package model;

import java.util.Date;

public class ViewBooking {
    private boolean isCancel;
    private int roomID;
    private int userId;
    private String bookingDate;
    private int slotID;
    private float price;
    private boolean isUsed;
    private String cancelDate;
    private String email;
    private int bookingId;
    public ViewBooking() {
    }
    
    public ViewBooking(boolean isCancel, int roomID, String bookingDate, int slotID, float price, boolean isUsed, String cancelDate, String email) {
        this.isCancel = isCancel;
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.slotID = slotID;
        this.price = price;
        this.isUsed = isUsed;
        this.cancelDate = cancelDate;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
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

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public boolean isIsCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }
}
