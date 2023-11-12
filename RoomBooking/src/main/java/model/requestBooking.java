package model;

import java.util.List;

public class requestBooking {
    public int idRoom;
    public String time;
    public int userId;
    public int slotId;
    public double amountEquipment;
    public double priceOfRoom;
    public List<Equipment> equipments;
    public String timePayment;
    public double amount;
    public requestBooking() {
    }

    public requestBooking(int idRoom, String time, int userId, List<Equipment> equipments) {
        this.idRoom = idRoom;
        this.time = time;
        this.userId = userId;
        this.equipments = equipments;
    }

    public requestBooking(int idRoom, String time, int userId, int slotId) {
        this.idRoom = idRoom;
        this.time = time;
        this.userId = userId;
        this.slotId = slotId;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public double getAmountEquipment() {
        return amountEquipment;
    }

    public void setAmountEquipment(double amountEquipment) {
        this.amountEquipment = amountEquipment;
    }

    public double getPriceOfRoom() {
        return priceOfRoom;
    }

    public void setPriceOfRoom(double priceOfRoom) {
        this.priceOfRoom = priceOfRoom;
    }

    public String getTimePayment() {
        return timePayment;
    }

    public void setTimePayment(String timePayment) {
        this.timePayment = timePayment;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
