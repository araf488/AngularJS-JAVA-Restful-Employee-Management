/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.User;
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
public class UserService {
    static HashMap<Integer, User> userIdMap = getUserIdMap();

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

    public List<Map<String, String>> getAllUsers() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select name, pass from user ");
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

    public User getUser(int id) {
        User user = userIdMap.get(id);

        if (user == null) {
            throw new ClassNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    public User addUser(User user) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from user where name=?, pass=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPass());

            ps.executeQuery();

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public User updateUser(User user) {
        if (user.getId() <= 0) {
            return null;
        }

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update user set name=?, pass=? where id=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPass());
            ps.setInt(3, user.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public void deleteUser(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from user where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, User> getUserIdMap() {
        return userIdMap;
    }

    public static int getMaxId() {
        int max = 0;
        for (int id : userIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }
        }
        return max;
    }
}
