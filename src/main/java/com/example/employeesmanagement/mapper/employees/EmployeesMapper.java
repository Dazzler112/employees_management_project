package com.example.employeesmanagement.mapper.employees;

import com.example.employeesmanagement.Dto.Employees;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeesMapper {

    @Select("""
            SELECT * FROM
            TB_EMPLOYEES
            WHERE id = #{id}
            """)
    @ResultMap("empMemberMap")
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
                       , is_active
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
                       , #{isActive}
                       )
            """)
    @ResultMap("signMember")
    Integer signUpInsert(Employees emp);

    @Insert("""
            INSERT INTO 
            FileName
                   (
                     employee_id
                   , fileName
                   )
            VALUES
                   (
                      #{id}
                    , #{fileName}
                   )
            """)
    Integer insertFileName(String id, String fileName);

    @Update("""
            <script>
            UPDATE TB_EMPLOYEES
            SET
            <if test="password neq null and password neq ''">
                password = #{password},
            </if>
              phone_number = #{phoneNumber}
            , department = #{department}
            , department_number = #{departmentNumber}
            , position = #{position}
            , email = #{email}
            , address = #{address}
            WHERE
            id = #{id}
            </script>
            """)
    int change(Employees emp);

    @Delete("""
            DELETE FROM FileName
            WHERE id = #{id}
            AND fileName = #{fileName}
            """)
    void deleteFileNameUpdate(String id, String fileName);

    @Insert("""
            INSERT INTO 
            FileName
                    (
                      id
                    , fileName
                    )
            VALUES (
                     #{id}
                   , #{fileName}  
                   )              
            """)
    Integer updateFileName(String id, String fileName);

    @Select("""
            SELECT 
                    id
                  , e.name
                  , e.department
                  , e.position
                  , f.fileName
            FROM
            TB_EMPLOYEES e LEFT JOIN
            FileName f ON e.id = f.employee_id
            WHERE e.id = #{id}      
            """)
    @ResultMap("getMemberInfo")
    Employees getEmployeeId(String id);
}
