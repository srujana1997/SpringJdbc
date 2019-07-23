package com.statckroute.springjdbc.configuration;

import com.statckroute.springjdbc.CrudOperationsDemo;
import com.statckroute.springjdbc.Employee;
import com.statckroute.springjdbc.JdbcTemplateDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //mysql driver
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/empdb"); //db connection
        driverManagerDataSource.setUsername("root"); // username
        driverManagerDataSource.setPassword("Root@123"); //password
        return driverManagerDataSource;
    }

    @Bean
    public Employee employee()
    {
        return new Employee();
    }

    @Bean
    public CrudOperationsDemo crudOperationsDemo()
    {
        return new CrudOperationsDemo();
    }

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public JdbcTemplateDemo jdbcTemplateDemo()
    {
        return new JdbcTemplateDemo();
    }
}
