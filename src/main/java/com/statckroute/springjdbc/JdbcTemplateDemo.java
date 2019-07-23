package com.statckroute.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateDemo {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateDemo()
    {

    }

    public int saveEmployee(Employee e){

        String query= "insert into employee values("+e.getId()+",'"+e.getName()+"',"+e.getAge()+",'"+e.getGender()+"')";
        return jdbcTemplate.update(query);
    }

    public void getEmployeeDetails()
    {
        try  {

            connection = dataSource.getConnection();

            String query = "select * from employee";

            ResultSet resultSet = connection.createStatement().executeQuery(query);

            //iterating resultset
            while(resultSet.next())
            {
                System.out.println("ID: " + resultSet.getInt("id")+"\t name: " +
                        resultSet.getString("name")+"\t age:"
                        +resultSet.getInt("age")+"\t gender:"
                        +resultSet.getString("gender"));
            }

        }catch (SQLException sqlException)
        {
            sqlException.printStackTrace();

            try {
                connection.rollback();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        finally {

            try {
                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }


    public int updateEmployee(int id, Employee employee)
    {
        String query="update employee set name = '" +employee.getName() +"', age = " +employee.getAge()+", gender = '"+employee.getGender() +"' where id ="+id;

        return jdbcTemplate.update(query);

    }

    public int deleteEmployee(int id)
    {
        String query="delete from employee where id="+id;

        return jdbcTemplate.update(query);
    }
}
