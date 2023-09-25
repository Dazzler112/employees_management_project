package com.example.employeesmanagement.mapper.employees;

import com.example.employeesmanagement.Dto.Employees;
import org.apache.ibatis.annotations.Insert;
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
//    @ResultMap("memberMap")
    Employees selectByEmployeesId(String id);

    @Insert("""
            INSERT INTO
            TB_EMPLOYEES
                       (
                         id
                       , employee_number  
                       , password
                       , name
                       , old
                       , gender
                       , phone_number
                       , department
                       , department_number
                       , position
                       , email
                       , member_type
                       , address
                       )
            VALUES           
                       (
                         #{id}
                       , #{employeeNumber}  
                       , #{password}
                       , #{name}
                       , #{old}
                       , #{gender}
                       , #{phoneNumber}
                       , #{department}
                       , #{departmentNumber}
                       , #{position}
                       , #{email}
                       , #{authority[0]}
                       , #{address}
                       )
            """)
    @ResultMap("signMember")
    Integer signUpInsert(Employees emp);

}
