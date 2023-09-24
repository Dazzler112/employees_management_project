package com.example.employeesmanagement.service.employees;

import com.example.employeesmanagement.Dto.Employees;

import java.util.Map;

public interface EmployeesService {
    public boolean signup(Employees emp);

    public Map<String, Object> checkId(String id);
}
