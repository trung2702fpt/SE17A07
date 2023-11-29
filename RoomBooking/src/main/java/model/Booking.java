
package model;

import java.util.List;

public class Booking {
    public int id;
    public String roomName;
    public int roomId;
    public int slotId;
    public int userId;
    public String dateBook;
    public boolean isCancel;
    public List<Equipment> equipments;
    public boolean isPaied;
    public String datePaied;
    public double priceRoom;
    public double amount;
    public boolean isOutTime;
    private String email;

    public Booking() {
    }

    public Booking(int id, int roomId, int slotId, int userId, String dateBook, boolean isCancel, boolean isPaied, String datePaied, double amount, double ptiveRoom, String roomName) {
        this.id = id;
        this.roomId = roomId;
        this.slotId = slotId;
        this.userId = userId;
        this.dateBook = dateBook;
        this.isCancel = isCancel;
        this.isPaied = isPaied;
        this.datePaied = datePaied;
        this.amount = amount;
        this.roomName = roomName;
    }
    
    public Booking(int id, int roomId, int slotId, int userId, String dateBook, boolean isCancel, List<Equipment> equipments, boolean isPaied, String datePaied, double amount) {
        this.id = id;
        this.roomId = roomId;
        this.slotId = slotId;
        this.userId = userId;
        this.dateBook = dateBook;
        this.isCancel = isCancel;
        this.equipments = equipments;
        this.isPaied = isPaied;
        this.datePaied = datePaied;
        this.amount = amount;
    }

    public boolean isIsOutTime() {
        return isOutTime;
    }

    public void setIsOutTime(boolean isOutTime) {
        this.isOutTime = isOutTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getPriceRoom() {
        return priceRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPriceRoom(double priceRoom) {
        this.priceRoom = priceRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateBook() {
        return dateBook;
    }

    public void setDateBook(String dateBook) {
        this.dateBook = dateBook;
    }

    public boolean isIsCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public boolean isIsPaied() {
        return isPaied;
    }

    public void setIsPaied(boolean isPaied) {
        this.isPaied = isPaied;
    }

    public String getDatePaied() {
        return datePaied;
    }

    public void setDatePaied(String datePaied) {
        this.datePaied = datePaied;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
