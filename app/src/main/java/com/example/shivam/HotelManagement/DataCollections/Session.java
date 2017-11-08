package com.example.shivam.HotelManagement.DataCollections;

/**
 * Created by lonewolf on 8/11/17.
 */

public class Session {
    private String sessionStatus;
    private User activeUser;

    public Session(String sessionStatus, User activeUser) {
        this.sessionStatus = sessionStatus;
        this.activeUser = activeUser;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}
