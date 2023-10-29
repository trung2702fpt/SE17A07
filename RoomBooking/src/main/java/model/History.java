package model;

import java.util.Date;

public class History {

    private int roomID;
    private Date bookingDate;
    private int slotID;
    private float price;
    private boolean isUsed;
    private Date cancelDate;

    public History() {
    }

    public History(int roomID, Date bookingDate, int slotID, float price, boolean isUsed, Date cancelDate) {
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

    public void setBookingDate(Date bookingDate) {
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

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Override
    public String toString() {
        return "History{" + "roomID=" + roomID + ", bookingDate=" + bookingDate + ", slotID=" + slotID + ", price=" + price + ", isUsed=" + isUsed + ", cancelDate=" + cancelDate + '}';
    }

}
