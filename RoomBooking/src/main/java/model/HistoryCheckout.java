/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class HistoryCheckout {
    private String userName;
    private int roomID;
    private Date bookingDate;
    private Date cancelDate;

    public HistoryCheckout(String userName, int roomID, Date bookingDate, Date cancelDate) {
        this.userName = userName;
        this.roomID = roomID;
        this.bookingDate = bookingDate;
        this.cancelDate = cancelDate;
    }

    // Getter và Setter cho các trường
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "userName='" + userName + '\'' +
                ", roomID=" + roomID +
                ", bookingDate=" + bookingDate +
                ", cancelDate=" + cancelDate +
                '}';
    }
}
