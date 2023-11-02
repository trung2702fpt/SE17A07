package model;

public class StaticBooking {
    private int persentCancelation;
    private int booking;
    private int cancel;

    public StaticBooking() {
    }

    public StaticBooking(int booking, int cancel) {
        this.booking = booking;
        this.cancel = cancel;
    }
    
    public StaticBooking(int persentCancelation, int booing, int cancel) {
        this.persentCancelation = persentCancelation;
        this.booking = booing;
        this.cancel = cancel;
    }

    public int getPersentCancelation() {
        return persentCancelation;
    }

    public void setPersentCancelation(int persentCancelation) {
        this.persentCancelation = persentCancelation;
    }

    public int getBooing() {
        return booking;
    }

    public void setBooing(int booing) {
        this.booking = booing;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }
    
}
