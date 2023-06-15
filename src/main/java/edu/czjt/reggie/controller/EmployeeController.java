ckage edu.czjt.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.czjt.reggie.common.R;
import edu.czjt.reggie.entity.Employee;
import edu.czjt.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *员工管理，梁涛
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("{} 登录了系统", employee.getUsername());
        // 1. 将pwd使用md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2. 根据username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 3. 如果没查到，返回登录失败
        if (emp == null) {
            return R.error("登录失败");
        }
        // 4. 如果不匹配，返回登录失败
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
            // return "登录失败";
        }
        // 5. 如果已禁用，返回账户已禁用
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
            // return "账号已禁用";
        }
        // 6. 登录成功，将员工ID存入Session中
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
        // return "登录成功";
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, name);
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getUsername, name);

        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping()
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee.toString());

        // 设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //员工创建时间，获取当前创建时间
        // employee.setCreateTime(LocalDateTime.now());
        //更新时间
        // employee.setUpdateTime(LocalDateTime.now());
        //获得当前登录的id
        // Long empId = (Long) request.getSession().getAttribute("employee");
        //当前创建人
        // employee.setCreateUser(empId);
        //最后创建人id
        // employee.setUpdateUser(empId);
        //继承接口
        employeeService.save(employee);

        return R.success("新增员工成功");
        //异常处理，在员工新增时，由于username是唯一的所以在名字重复是出现错误，由于异常可能较多此方法不好
        //此方法弊端是每一个异常都需写一遍，所以编写一个全局异常处理
        /*return R.success("新增员工成功");
        try{
            employeeService.save(employee);

        }catch (Exception ex){
            R.error(“新增员工失败”);
        }
        return R.success("新增员工成功");*/
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("更新的员工信息{}", employee);

        // Long empId = (Long) request.getSession().getAttribute("employee");
        // employee.setUpdateUser(empId);
        // employee.setUpdateTime(LocalDateTime.now());

        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工信息...");

        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("员工不存在");
    }
