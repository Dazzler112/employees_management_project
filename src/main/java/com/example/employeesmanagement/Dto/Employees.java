package com.example.employeesmanagement.Dto;

import lombok.Data;

import java.util.List;

@Data
public class Employees {
    private String id;
    private Integer employeeNumber;
    private String password;
    private String name;
    private Integer old;
    private String gender;
    private String phoneNumber;
    private String department;
    private String depatementNumber;
    private String position;
    private String email;
    private String memberType;
    private String address;
    private List<String> authority;
    private List<String> fileName;
}
