package com.example.employeesmanagement.service.employees;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.mapper.employees.EmployeesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import software.amazon.awssdk.core.sync.*;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class EmployeesServiceImpl implements EmployeesService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeesMapper empMapper;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private S3Client s3;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean signup(Employees emp, MultipartFile[] files) throws IOException {
        //비밀번호 암호화
        String plain = emp.getPassword();
        emp.setPassword(passwordEncoder.encode(plain));

        int cnt = empMapper.signUpInsert(emp);
        for(MultipartFile file : files) {
            if(file.getSize() > 0){
                empMapper.insertFileName(emp.getId(), file.getOriginalFilename());
                String ObjectKey = "employees_management/" + emp.getId() + "/" + file.getOriginalFilename();

                PutObjectRequest por = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(ObjectKey)
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .build();
                RequestBody rb = RequestBody.fromInputStream(file.getInputStream(),file.getSize());

                s3.putObject(por,rb);
            }
        }

        return cnt == 1;
    }

    @Override
    public Map<String, Object> checkId(String id){
        Employees emp = empMapper.selectByEmployeesId(id);
        boolean available = (emp == null);
        return Map.of("available", emp == null);
    }

    @Override
    public Employees get(String id) {
        return empMapper.selectByEmployeesId(id);
    }

    @Override
    @Transactional()
    public boolean changeAccount(Employees emp, String oldPassword, List<String> removeFileNames, MultipartFile[] addFile) throws Exception{

        if(removeFileNames != null && !removeFileNames.isEmpty()) {
            for (String fileName : removeFileNames) {
                String objectKey = "employees_management/" + emp.getId() + "/" + fileName;
                DeleteObjectRequest dor = DeleteObjectRequest
                        .builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                s3.deleteObject(dor);

                empMapper.deleteFileNameUpdate(emp.getId(), fileName);
            }
        }


        for(MultipartFile file : addFile) {
            if(file.getSize() > 0) {
                empMapper.updateFileName(emp.getId(), file.getOriginalFilename());

                String objectKey = "employees_management/" + emp.getId() + "/" + file.getOriginalFilename();
                PutObjectRequest por = PutObjectRequest
                        .builder()
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                RequestBody rb = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
                s3.putObject(por, rb);
            }
        }


        if(!emp.getPassword().isBlank()) {

            String encryption = emp.getPassword();
            emp.setPassword(passwordEncoder.encode(encryption));
        }

        Employees oldMember = empMapper.selectByEmployeesId(emp.getId());
        int cnt = 0;
        if(passwordEncoder.matches(oldPassword, oldMember.getPassword())){
            cnt = empMapper.change(emp);
        }
        return cnt == 1;
    }
}
