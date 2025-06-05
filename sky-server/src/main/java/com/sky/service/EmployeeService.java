package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param dto
     */
    void addEmployee(EmployeeDTO dto);

    /**
     * 员工分页查询
     * @param dto
     * @return
     */
    PageResult getEmpPage(EmployeePageQueryDTO dto);

    /**
     * 设置员工账号启用状态
     * @param status
     * @param id
     */
    void setEnableStatus(Integer status, Integer id);
}
