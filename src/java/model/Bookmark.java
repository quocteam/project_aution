/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author quoc9
 */
public class Bookmark {
    private int id;
    private String sessionID;
    private String userID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Bookmark() {
    }

    public Bookmark(int id, String sessionID, String userID) {
        this.id = id;
        this.sessionID = sessionID;
        this.userID = userID;
    }
    
}
