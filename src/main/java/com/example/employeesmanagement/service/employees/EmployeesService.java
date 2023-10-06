package com.example.employeesmanagement.service.employees;

import com.example.employeesmanagement.Dto.Employees;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface EmployeesService {
    public boolean signup(Employees emp,MultipartFile[] files) throws IOException;

    public Map<String, Object> checkId(String id);

    public Employees get(String id);

    public boolean changeAccount(Employees emp, String oldPassword, List<String> removeFileNames, MultipartFile[] addFile) throws Exception;

//    public Employees getProcess(String id, Authentication authentication);
}
