package com.example.employeesmanagement.security;

import com.example.employeesmanagement.Dto.Employees;
import com.example.employeesmanagement.mapper.employees.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final EmployeesMapper mapper;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        Employees employees = mapper.selectByEmployeesId(username);

        if (employees == null) {
            throw new UsernameNotFoundException(username + "해당 회원 조회불가");
        }

        boolean isActive = employees.getIsActive() == 1;

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        for (String auth : employees.getAuthority()){
            authorityList.add(new SimpleGrantedAuthority(auth));
        }

        UserDetails user = User.builder()
                .username(employees.getId())
                .disabled(!isActive)
                .password(employees.getPassword())
                .authorities(List.of())
                .authorities(employees.getAuthority().stream().map(SimpleGrantedAuthority::new).toList())
                .build();

        return user;
    }
}
