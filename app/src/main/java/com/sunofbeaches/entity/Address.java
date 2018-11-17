package com.sunofbeaches.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

public class Address extends BmobObject {

    public String currentProvinceName;
    public String currentCityName;
    public String currentDistrictName;
    public String recv_name;
    public int phonenuber;
    public int currentZipCode;
    public String project;
    public String expresscompany;
    public String detailsaddress;

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

    public int getPhonenuber() {
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

    public void setPhonenuber(int phonenuber) {
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

}
