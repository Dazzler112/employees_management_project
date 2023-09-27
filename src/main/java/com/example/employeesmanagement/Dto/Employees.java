package com.example.employeesmanagement.Dto;

import lombok.Data;

import java.util.List;

@Data
public class Employees {
    private String id;
    private String employeeNumber;
    private String password;
    private String name;
    private String old;
    private String gender;
    private String phoneNumber;
    private String department;
    private String departmentNumber;
    private String position;
    private String email;
    private String memberType;
    private String address;
    private Integer isActive;
    private List<String> authority;
    private List<String> fileName;
}
