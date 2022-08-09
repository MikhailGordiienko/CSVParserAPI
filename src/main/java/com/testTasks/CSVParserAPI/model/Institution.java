package com.testTasks.CSVParserAPI.model;

import com.testTasks.CSVParserAPI.entity.InstitutionEntity;

public class Institution {
    private String state;
    private String name;
    private String institution;
    private String phoneNumber;
    private String webSite;

    public static Institution toModel(InstitutionEntity entity){
        Institution model = new Institution();
        model.setState(entity.getState());
        model.setName(entity.getName());
        model.setInstitution(entity.getInstitution());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setWebSite(entity.getWebSite());
        return model;
    }

    public Institution() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
