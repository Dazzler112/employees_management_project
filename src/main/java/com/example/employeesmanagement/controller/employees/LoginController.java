package com.example.employeesmanagement.controller.employees;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.service.employees.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees/")
public class LoginController {

    private final UserPageService userService;

    @GetMapping("login")
    @PreAuthorize("isAnonymous()")
    public String loginForm(Employees emp) {
        System.out.println("emp액티브 =>" + emp.getIsActive());

        return "employees/login";
    }
}
