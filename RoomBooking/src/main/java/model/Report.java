package model;

public class Report {
    private int reportID;
    private int userID;
    private String time;
    private String title;
    private String content;
    private boolean status;
    private String reply;

    public Report() {
    }

    public Report(int reportID, int userID, String time, String title, String content, boolean status, String reply) {
        this.reportID = reportID;
        this.userID = userID;
        this.time = time;
        this.title = title;
        this.content = content;
        this.status = status;
        this.reply = reply;
    }
    
    public Report(int userID, String time, String title, String content, boolean status, String reply) {
        this.userID = userID;
        this.time = time;
        this.title = title;
        this.content = content;
        this.status = status;
        this.reply = reply;
    }

    public int getReportID() {
        return reportID;
    }

    public int getUserID() {
        return userID;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getStatus() {
        return status;
    }

    public String getReply() {
        return reply;
    }
}
