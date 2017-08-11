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
public class BetHistory {
    public String sessionId;
    public String productName;
    public int value;
    public String betStatus;
    public String userStatus;

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BetHistory() {
    }

    public BetHistory(String sessionId, String productName, int value, String betStatus, String userStatus) {
        this.sessionId = sessionId;
        this.productName = productName;
        this.value = value;
        this.betStatus = betStatus;
        this.userStatus = userStatus;
    }

    
    
}
