/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author B14
 */
public class EmployeeService {
    static HashMap<Integer, Employee> employeeIdMap = getEmployeeIdMap();

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrmanagement", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public List<Map<String, String>> getAllEmployees() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);

            }
            System.out.println(list);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Employee getEmployee(int id) {
        Employee employee = employeeIdMap.get(id);

        if (employee == null) {
            throw new ClassNotFoundException("Employee with id " + id + " not found");
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into employee (ename, full_name, "
                    + "email, mobile, address, gender, salary, hire_date, rank)values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, employee.getEname());
            ps.setString(2, employee.getFull_name());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getMobile());
            ps.setString(5, employee.getAddress());
            ps.setString(6, employee.getGender());
            ps.setDouble(7, employee.getSalary());
            ps.setString(8, employee.getHire_date());
            ps.setString(9, employee.getRank());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return employee;
    }

    public Employee updateEmployee(Employee employee) {
        if (employee.getId() <= 0) {
            return null;
        }

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update employee set ename=?, full_name=?, "
                    + "email=?, mobile=?, address=?, gender=?, salary=?, hire_date=?, rank=? where id=?");
            ps.setString(1, employee.getEname());
            ps.setString(2, employee.getFull_name());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getMobile());
            ps.setString(5, employee.getAddress());
            ps.setString(6, employee.getGender());
            ps.setDouble(7, employee.getSalary());
            ps.setString(8, employee.getHire_date());
            ps.setString(9, employee.getRank());
            ps.setInt(10, employee.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return employee;
    }

    public void deleteEmployee(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from employee where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, Employee> getEmployeeIdMap() {
        return employeeIdMap;
    }

    public static int getMaxId() {
        int max = 0;
        for (int id : employeeIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }
        }
        return max;
    }
}
