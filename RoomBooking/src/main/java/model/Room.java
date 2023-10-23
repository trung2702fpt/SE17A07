/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Room {
    public int id;
    public String roomNumber;
    public double price;

    public Room() {
    }
    
    public Room(int id) {
        this.id = id;
    }

    public Room(int id, String roomNumber, double price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", roomNumber=" + roomNumber + ", price=" + price + '}';
    }
    
            
}
