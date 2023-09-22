package com.example.employeesmanagement.service.employees;

import com.example.employeesmanagement.mapper.employees.EmployeesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPageServiceImpl implements UserPageService{

    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
}
