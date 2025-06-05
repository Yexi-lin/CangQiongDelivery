package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@Api(tags = "员工管理 EmployeeController")
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation("员工登录接口 login")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("员工登录接口 logout")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     * @param dto
     * @return
     */
    @ApiOperation("新增员工接口 addEmployee")
    @PostMapping()
    public Result addEmployee(@RequestBody EmployeeDTO dto){
        employeeService.addEmployee(dto);
        return Result.success();
    }

    /**
     * 员工分页查询
     * @param dto
     * @return
     */
    @ApiOperation("员工分页查询 getEmpPage")
    @GetMapping("/page")
    public Result<PageResult> getEmpPage(EmployeePageQueryDTO dto){
        PageResult pageResult = employeeService.getEmpPage(dto);
        return Result.success(pageResult);
    }

    /**
     * 设置员工账号启用状态
     * @param status
     * @param id
     * @return
     */
    @ApiOperation("设置员工账号启用状态 setEnableStatus")
    @PostMapping("/status/{status}")
    public Result setEnableStatus(@PathVariable Integer status, Integer id){
        employeeService.setEnableStatus(status, id);
        return Result.success();
    }
}
