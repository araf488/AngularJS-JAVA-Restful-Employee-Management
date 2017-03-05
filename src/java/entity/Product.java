/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author B14
 */
public class Product {
    int id;
    String pname;
    String model;
    int qty;
    double p_price;
    double s_price;
    String p_date;

    public Product() {
        super();
    }

    public Product(int id, String pname, String model, int qty, double p_price, double s_price, String p_date) {
        super();
        this.id = id;
        this.pname = pname;
        this.model = model;
        this.qty = qty;
        this.p_price = p_price;
        this.s_price = s_price;
        this.p_date = p_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }
}
