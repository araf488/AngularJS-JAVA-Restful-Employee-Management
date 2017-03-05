/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
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
public class ProductService {
    static HashMap<Integer, Product> productIdMap = getProductIdMap();

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

    public List<Map<String, String>> getAllProducts() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from product");
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

    public Product getProduct(int id) {
        Product product = productIdMap.get(id);

        if (product == null) {
            throw new ClassNotFoundException("Product with id " + id + " not found");
        }
        return product;
    }

    public Product addProduct(Product product) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into product (pname, model, qty, "
                    + "p_price, s_price, p_date)values(?,?,?,?,?,?)");
            ps.setString(1, product.getPname());
            ps.setString(2, product.getModel());
            ps.setInt(3, product.getQty());
            ps.setDouble(4, product.getP_price());
            ps.setDouble(5, product.getS_price());
            ps.setString(6, product.getP_date());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return product;
    }

    public Product updateProduct(Product product) {
        if (product.getId() <= 0) {
            return null;
        }

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update product set pname=?, model=?, qty=?, "
                    + "p_price=?, s_price=?, p_date=? where id=?");
            ps.setString(1, product.getPname());
            ps.setString(2, product.getModel());
            ps.setInt(3, product.getQty());
            ps.setDouble(4, product.getP_price());
            ps.setDouble(5, product.getS_price());
            ps.setString(6, product.getP_date());
            ps.setInt(7, product.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return product;
    }

    public void deleteProduct(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from product where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, Product> getProductIdMap() {
        return productIdMap;
    }

    public static int getMaxId() {
        int max = 0;
        for (int id : productIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }
        }
        return max;
    }
}
