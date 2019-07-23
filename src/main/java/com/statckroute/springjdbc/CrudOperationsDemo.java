package com.statckroute.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class CrudOperationsDemo {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    private JdbcTemplate jdbcTemplate;

    public CrudOperationsDemo()
    {

    }

    public void addEmployee(Employee employee)
    {
        try  {

            //establishes connection using datasource
            connection = dataSource.getConnection();

            String insertTableSQL = "insert into employee values(?,?,?,?)";

            //craeting prepared statement
            PreparedStatement preparedStatementInsert = connection.prepareStatement(insertTableSQL);

            connection.setAutoCommit(false);

            //setting each value of prepared statement
            preparedStatementInsert.setInt(1,employee.getId());

            preparedStatementInsert.setString(2,employee.getName());

            preparedStatementInsert.setInt(3,employee.getAge());

            preparedStatementInsert.setString(4, employee.getGender());

            preparedStatementInsert.executeUpdate();

            connection.commit();

            System.out.println("Inserted Sucessfully !!!!!");

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


    public void updateEmployee(int id, Employee employee)
    {
        try  {

            connection = dataSource.getConnection();

            String updateTableSQL = "update employee set name = ?, age = ?, gender = ? where id = ?";

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateTableSQL);

            connection.setAutoCommit(false);

            preparedStatementUpdate.setString(1,employee.getName());

            preparedStatementUpdate.setInt(2,employee.getAge());

            preparedStatementUpdate.setString(3, employee.getGender());

            preparedStatementUpdate.setInt(4,id);

            //executing prepared statement
            preparedStatementUpdate.executeUpdate();

            connection.commit();

            System.out.println("Updated Sucessfully !!!!!");

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


    public void deleteEmployee(int id)
    {
        try  {

            connection = dataSource.getConnection();

            String deleteTableSQL = "delete from employee where id = ?";

            PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteTableSQL);

            connection.setAutoCommit(false);

            preparedStatementDelete.setInt(1,id);

            preparedStatementDelete.executeUpdate();

            connection.commit();

            System.out.println("Deleted Sucessfully !!!!!");

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





}
