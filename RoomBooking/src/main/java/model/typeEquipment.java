/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class typeEquipment {
    public int id;
    public String name;

    public typeEquipment(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public typeEquipment() {
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

    @Override
    public String toString() {
        return "typeEquipment{" + "id=" + id + ", name=" + name + '}';
    }
}
