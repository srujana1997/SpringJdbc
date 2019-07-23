package com.statckroute.springjdbc;

import com.statckroute.springjdbc.configuration.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Employee employee = applicationContext.getBean("employee", Employee.class);

        employee.setId(5);

        employee.setName("karthik");

        employee.setAge(25);

        employee.setGender("m");

        CrudOperationsDemo crudOperationsDemo = applicationContext.getBean("crudOperationsDemo", CrudOperationsDemo.class);

        //create employee
        crudOperationsDemo.addEmployee(employee);

        //select employee
        crudOperationsDemo.getEmployeeDetails();

        //update employee by id
        crudOperationsDemo.updateEmployee(5, new Employee(5,"karthik",25,"m"));

        crudOperationsDemo.getEmployeeDetails();

        //delete employee by id
        crudOperationsDemo.deleteEmployee(5);

        System.out.println("USING JDBC TEMPLATE");

        Employee employee1 = applicationContext.getBean("employee", Employee.class);

        employee.setId(6);

        employee.setName("priya");

        employee.setAge(22);

        employee.setGender("f");

        JdbcTemplateDemo jdbcTemplateDemo = applicationContext.getBean("jdbcTemplateDemo",JdbcTemplateDemo.class);

        jdbcTemplateDemo.deleteEmployee(6);

        System.out.println("deleted sucessfully!!!");

        jdbcTemplateDemo.saveEmployee(employee);

        System.out.println("inserted sucessfully!!!");

        jdbcTemplateDemo.getEmployeeDetails();

        jdbcTemplateDemo.updateEmployee(6,new Employee(6,"priyanka",22,"f"));

        jdbcTemplateDemo.getEmployeeDetails();

    }
}
