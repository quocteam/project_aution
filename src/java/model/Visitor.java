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
public class Visitor {
    private String operationSystem;
    private String browser;
    private String ipAddress;
    private String time;

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Visitor() {
    }

    public Visitor(String operationSystem, String browser, String ipAddress, String time) {
        this.operationSystem = operationSystem;
        this.browser = browser;
        this.ipAddress = ipAddress;
        this.time = time;
    }
    
}
