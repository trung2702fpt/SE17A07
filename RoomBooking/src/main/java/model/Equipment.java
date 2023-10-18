/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Equipment {
    public int id;
    public String name;
    public String des;
    public double price;
    public typeEquipment type;

    public Equipment() {
    }

    public Equipment(int id, String name, String des, double price, typeEquipment type) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public typeEquipment getType() {
        return type;
    }

    public void setType(typeEquipment type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Equipment{" + "id=" + id + ", name=" + name + ", des=" + des + ", price=" + price + ", type=" + type + '}';
    }
    
}
