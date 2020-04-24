package com.tth.springboot;

import com.tth.springboot.dao.EmployeeDao;
import com.tth.springboot.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Iterator;

@SpringBootTest
class SpringBootWeb04RestfulcrudApplicationTests {

    @Test
        void contextLoads() {
            EmployeeDao employeeDao = new EmployeeDao();
            Collection<Employee> all = employeeDao.getAll();
            Iterator<Employee> iterator = all.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
    }

}
