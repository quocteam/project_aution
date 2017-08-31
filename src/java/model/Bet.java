/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author quoc95
 */
public class Bet {
    private int id;
    private String sessionId;
    private String userBetId;
    private String userName;
    private int value;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserBetId() {
        return userBetId;
    }

    public void setUserBetId(String userBetId) {
        this.userBetId = userBetId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Bet(int id, String sessionId, String userBetId, int value,String username) {
        this.id = id;
        this.sessionId = sessionId;
        this.userBetId = userBetId;
        this.value = value;
        this.userName=username;
    }

    public Bet() {
    }
    
    
}
