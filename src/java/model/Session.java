/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author quoc95
 */
public class Session {
    private String sessionId;
    private String userCreateID;
    private String productName;
    private String productType;
    private String productInformation;
    private float startPrice;
    private float stepPrice;
    private int bid;
    private float lastPrice;
    private String userWinID;
    private String startTime;
    private String endTime;
    private String avatar;
    private String status;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserCreateID() {
        return userCreateID;
    }

    public void setUserCreateID(String userCreateID) {
        this.userCreateID = userCreateID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductInformation() {
        return productInformation;
    }

    public void setProductInformation(String productInformation) {
        this.productInformation = productInformation;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(float stepPrice) {
        this.stepPrice = stepPrice;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getUserWinID() {
        return userWinID;
    }

    public void setUserWinID(String userWinID) {
        this.userWinID = userWinID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Session() {
    }

    public Session(String sessionId, String userCreateID, String productName, String productType, String productInformation, float startPrice, float stepPrice, int bid, float lastPrice, String userWinID, String startTime, String endTime, String status,String avatar) {
        this.sessionId = sessionId;
        this.userCreateID = userCreateID;
        this.productName = productName;
        this.productType = productType;
        this.productInformation = productInformation;
        this.startPrice = startPrice;
        this.stepPrice = stepPrice;
        this.bid = bid;
        this.lastPrice = lastPrice;
        this.userWinID = userWinID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.avatar = avatar;
    }
    
}
