package com.example.employeesmanagement.service.employees;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.mapper.employees.EmployeesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class EmployeesServiceImpl implements EmployeesService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeesMapper empMapper;

    @Override
    public boolean signup(Employees emp) {
        //비밀번호 암호화
        String plain = emp.getPassword();
        emp.setPassword(passwordEncoder.encode(plain));

        int cnt = empMapper.signUpInsert(emp);

        return cnt ==1;
    }

    @Override
    public Map<String, Object> checkId(String id){
        Employees emp = empMapper.selectByEmployeesId(id);
        boolean available = (emp == null);
        return Map.of("available", emp == null);
    }
}
