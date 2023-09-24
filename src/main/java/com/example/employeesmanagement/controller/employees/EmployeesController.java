package com.example.employeesmanagement.controller.employees;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.service.employees.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/employees/")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping("signature")
    public void signUpForm(){

    }

    @PostMapping("signature")
    public String signUpProcess(Employees emp, RedirectAttributes rttr){
        try{
            employeesService.signup(emp);
            rttr.addFlashAttribute("","멤버 생성 완료");
            System.out.println("생성이 완료됐습니다.");
            return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
            rttr.addFlashAttribute("employees",emp);
            System.out.println("생성에 실패했습니다.");
            return "redirect:/";
        }
    }

    @GetMapping("checkId/{id}")
    @ResponseBody
    public Map<String,Object> checkId(@PathVariable("id") String id) {
        return employeesService.checkId(id);
    }
}
