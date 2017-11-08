package com.example.shivam.HotelManagement.DataCollections;

/**
 * Created by lonewolf on 8/11/17.
 */

public class Session {
    private User activeUser;
    private boolean isActive = false;

    public void clearSession() {
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Session(User activeUser) {
        this.activeUser = activeUser;
        this.isActive = true;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}
