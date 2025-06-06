package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void addEmployee(Employee employee);

    /**
     * 获取员工分页列表
     * @param dto
     * @return
     */
    Page<Employee> getEmpPage(EmployeePageQueryDTO dto);

//    合并为员工数据更新
//
//    /**
//     * 设置员工账号启用状态
//     * @param status
//     * @param id
//     */
//    @Update("update employee set status = #{status} where id = #{id}")
//    void setEnableStatus(Integer status, Integer id);

    /**
     * 根据Id查询员工
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(Long id);

    /**
     * 更新员工数据
     * @param employee
     */
    @AutoFill(OperationType.UPDATE)
    void updateEmployee(Employee employee);
}
