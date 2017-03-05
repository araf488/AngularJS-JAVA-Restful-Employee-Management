/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Salary;
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
public class SalaryService {
    static HashMap<Integer, Salary> salaryIdMap = getSalaryIdMap();

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

    public List<Map<String, String>> getAllSalarys() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from salary");
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

    public Salary getSalary(int id) {
        Salary salary = salaryIdMap.get(id);

        if (salary == null) {
            throw new ClassNotFoundException("Salary with id " + id + " not found");
        }
        return salary;
    }

    public Salary addSalary(Salary salary) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into salary (ename, month, year, "
                    + "salary, status)values(?,?,?,?,?)");
            ps.setString(1, salary.getEname());
            ps.setString(2, salary.getMonth());
            ps.setInt(3, salary.getYear());
            ps.setDouble(4, salary.getSalary());
            ps.setString(5, salary.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return salary;
    }

    public Salary updateSalary(Salary salary) {
        if (salary.getId() <= 0) {
            return null;
        }

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update salary set ename=?, month=?, year=?, "
                    + "salary=?, status=? where id=?");
            ps.setString(1, salary.getEname());
            ps.setString(2, salary.getMonth());
            ps.setInt(3, salary.getYear());
            ps.setDouble(4, salary.getSalary());
            ps.setString(5, salary.getStatus());
            ps.setInt(6, salary.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return salary;
    }

    public void deleteSalary(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from salary where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, Salary> getSalaryIdMap() {
        return salaryIdMap;
    }

    public static int getMaxId() {
        int max = 0;
        for (int id : salaryIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }
        }
        return max;
    }
}
