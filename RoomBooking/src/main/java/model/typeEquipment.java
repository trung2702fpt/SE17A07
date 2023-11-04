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
}
