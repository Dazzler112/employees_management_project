package com.example.employeesmanagement.controller.employees;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.service.employees.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String signUpProcess(@RequestParam("fileList") MultipartFile[] files,
                                Employees emp,
                                RedirectAttributes rttr){
        try{
            employeesService.signup(emp,files);
            rttr.addFlashAttribute("","멤버 생성 완료");
            System.out.println("생성이 완료됐습니다.");
            return "redirect:/employees/login";
        }catch (Exception e){
            e.printStackTrace();
            rttr.addFlashAttribute("employees",emp);
            System.out.println("생성에 실패했습니다.");
            return "redirect:/employees/signature";
        }
    }

    @GetMapping("checkId/{id}")
    @ResponseBody
    public Map<String,Object> checkId(@PathVariable("id") String id) {
        return employeesService.checkId(id);
    }

    @GetMapping("change")
    @PreAuthorize("hasAnyAuthority('admin') or isAuthenticated()")
    public void changeForm(String id, Model model) {
        Employees emp = employeesService.get(id);
        model.addAttribute("emp",emp);
    }

    @PostMapping("change")
    @PreAuthorize("isAuthenticated()")
    public String change (Employees emp , RedirectAttributes rttr, String oldPassword) {
        boolean ok = employeesService.changeAccount(emp, oldPassword);
        if(ok) {
            rttr.addFlashAttribute("message", "변경 완료.");
            return "redirect:/employees/login";
        } else {
            rttr.addFlashAttribute("message", "변경 실패.");
            return "redirect:/employees/change?id=" + emp.getId();
        }
    }
}
