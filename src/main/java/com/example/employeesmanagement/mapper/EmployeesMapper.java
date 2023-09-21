package com.example.employeesmanagement.mapper;

import com.example.employeesmanagement.Dto.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeesMapper {

    @Select("""
            SELECT * FROM
            TB_EMPLOYEES
            WHERE id = #{id}
            """)
    @ResultMap("memberMap")
    Employees selectByEmployeesId(String id);
}
