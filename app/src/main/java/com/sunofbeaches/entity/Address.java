package com.sunofbeaches.entity;

import cn.bmob.v3.BmobObject;

public class Address extends BmobObject {

    public String currentProvinceName;
    public String currentCityName;
    public String currentDistrictName;
    public String recv_name;
    public Long phonenuber;
    public int currentZipCode;
    public String project;
    public String expresscompany;
    public String detailsaddress;
    public String sendname;
    public String expressnumber;
    public boolean active;

    public String getCurrentProvinceName()
    {
        return currentProvinceName;
    }

    public String getExpresscompany() {
        return expresscompany;
    }

    public int getCurrentZipCode() {
        return currentZipCode;
    }

    public Long getPhonenuber() {
        return phonenuber;
    }

    public String getCurrentCityName() {
        return currentCityName;
    }

    public String getDetailsaddress()
    {
        return detailsaddress;
    }
    public String getCurrentDistrictName() {
        return currentDistrictName;
    }

    public String getProject() {
        return project;
    }

    public String getRecv_name() {
        return recv_name;
    }

    public String getExpressnumber()
    {
        return expressnumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setCurrentCityName(String currentCityName) {
        this.currentCityName = currentCityName;
    }

    public void setCurrentDistrictName(String currentDistrictName) {
        this.currentDistrictName = currentDistrictName;
    }

    public void setCurrentProvinceName(String currentProvinceName) {
        this.currentProvinceName = currentProvinceName;
    }

    public void setCurrentZipCode(int currentZipCode) {
        this.currentZipCode = currentZipCode;
    }

    public void setPhonenuber(Long phonenuber) {
        this.phonenuber = phonenuber;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setRecv_name(String recv_name) {
        this.recv_name = recv_name;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany;
    }

    public void setDetailsaddress(String detailsaddress) {
        this.detailsaddress = detailsaddress;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public void setExpressnumber(String expressnumber) {
        this.expressnumber = expressnumber;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
