package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对
        // 将传入的密码进行md5运算
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     * @param dto
     */
    @Override
    public void addEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        //将入参装配进员工对象
        BeanUtils.copyProperties(dto, employee);
        //设置密码默认值 并用md5处理
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置账号默认为启用状态
        employee.setStatus(StatusConstant.ENABLE);
        //时间和操作人员数据填充改为AOP实现
        //设置创建时间和修改时间
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        //设置创建人和修改人
        //从ThreadLocal中调用拦截器获取的当前员工id
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        employeeMapper.addEmployee(employee);
    }

    /**
     * 员工分页查询
     * @param dto
     * @return
     */
    @Override
    public PageResult getEmpPage(EmployeePageQueryDTO dto) {
        //1.启用分页插件
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        //2.查询sql获取emp集合 并封装为Page
        Page<Employee> pageList = employeeMapper.getEmpPage(dto);
        //获取分页的数据总数和列表
        long total = pageList.getTotal();
        List<Employee> result = pageList.getResult();
        return new PageResult(total, result);
    }

    /**
     * 设置员工账号启用状态
     * @param status
     * @param id
     */
    @Override
    public void setEnableStatus(Integer status, Long id) {
        //封装为Employee对象,直接调用更新员工数据接口
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(status);
        employeeMapper.updateEmployee(employee);
    }

    /**
     * 根据id获取员工信息
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeMapper.getEmployeeById(id);
        return employee;
    }

    /**
     * 更新员工信息
     * @param dto
     */
    @Override
    public void updateEmployee(EmployeeDTO dto) {
        //将传入的dto转换为Employee对象
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        //设置修改时间和修改人
        //改为AOP实现
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(BaseContext.getCurrentId());
        employeeMapper.updateEmployee(employee);
    }

}
