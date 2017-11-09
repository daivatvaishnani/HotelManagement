package com.example.shivam.HotelManagement.DataCollections;

/**
 * Created by lonewolf on 8/11/17.
 */

public class Session {
    private User activeUser;

    public void clearSession() {
        this.activeUser = null;
    }

    public boolean isActive() {
        if(this.activeUser == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public Session(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}
