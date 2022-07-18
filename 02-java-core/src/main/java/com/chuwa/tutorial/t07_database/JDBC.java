package com.chuwa.tutorial.t07_database;

import com.chuwa.tutorial.t00_common.pojos.Employee;

import java.sql.*;

/**
 * @author b1go
 * @date 6/14/22 11:48 PM
 */
public class JDBC {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3361/EMP";
    private static final String USERNAME = "chuwa_geek";
    private static final String PASSWORD = "chuwa_yyds";

    public Employee getEmployeeById(int id) throws Exception {
        Employee employee = new Employee();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1, load Driver
            Class.forName(DRIVER);
            // 2， connect to Database;
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 3， define sql statement
//            String sql = "SELECT * FROM emp WHERE ID = " + id;
            // 4, create a statement object
            stmt = conn.createStatement();
            // 5, use stmt object to execute sql statement;
//            rs = stmt.executeQuery(sql); // the result is return to ResultSet

            while(rs.next()) {
                // 6, get ResultSet's data to java object(employee)
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
            }

            // 7, close conections and other resource.
            rs.close();
            stmt.close();
            conn.close();

            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }

        return null;
    }
}
