package model;

import java.sql.Timestamp;
import java.util.Date;

public class History {

    private int roomID;
    private Timestamp bookingDate;
    private int slotID;
    private float price;
    private boolean isUsed;
    private Timestamp cancelDate;

    public History() {
    }

    public History(int roomID, Timestamp bookingDate, int slotID) {
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.slotID = slotID;
    }
    
    public History(int roomID, Timestamp bookingDate, int slotID, Timestamp cancelDate) {
        this.roomID = roomID;
        this.slotID = slotID;
        this.cancelDate = cancelDate;
        this.bookingDate = bookingDate;
    }
    
    public History(int roomID, Timestamp bookingDate, int slotID, float price, boolean isUsed, Timestamp cancelDate) {
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.slotID = slotID;
        this.price = price;
        this.isUsed = isUsed;
        this.cancelDate = cancelDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Timestamp cancelDate) {
        this.cancelDate = cancelDate;
    }
}
