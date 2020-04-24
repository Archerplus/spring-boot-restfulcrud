package com.tth.springboot.controller;

import com.tth.springboot.dao.DepartmentDao;
import com.tth.springboot.dao.EmployeeDao;
import com.tth.springboot.entities.Department;
import com.tth.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.util.Collection;

@Controller
public class EmpController {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //查询所有员工，返回列表页面
    @GetMapping("/emps")
    public String emps(Model model){
        Collection<Employee> empList = employeeDao.getAll();
        //放在请求域中进行共享
        model.addAttribute("emps",empList);
        //thymeleaf默认就会进行拼串
//        classpath:/templates/xxx.html
        return "emps/list";
    }
    //来到员工添加页面
    @GetMapping("emp")
    public String toAddPage(Model model){
        //来到添加页面，查出所有部门，在页面提示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emps/add";
    }
    //员工添加
    //Spring MVC自动将请求参数和参数对象的属性进行一一绑定；要求就是请求参数的名字和javabean入参的对象里面的属性名一致
    @PostMapping("emp")
    public String addEmp(Employee employee){
        //来到员工列表页面
        //redirect: 表示重定向到一个地址  /代表当前项目路径
        //forward：表示转发一个地址
        System.out.println("保存的员工信息：" + employee);
        //保存员工的信息
        employeeDao.save(employee);
        return "redirect:emps";
    }
    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        System.out.println(employee);
        model.addAttribute("emp",employee);
        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面)
        return "emps/add";
    }
    //员工修改：需要提交员工id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        System.out.println("员工的id是 " + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
