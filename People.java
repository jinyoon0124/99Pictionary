package com.example.jinyoon.pictionary;

/**
 * Created by Jin Yoon on 7/21/2016.
 */
public class People {

    private String DEFAULT_ROUTE="file:///android_asset/atheletes_paid_img";


    private String id;
    private String name;
    private String imageRoute;
    private String field;
    private String citizenship;
    private String salary;
    private String endorsement;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageRoute() {
        return DEFAULT_ROUTE+imageRoute;
    }

    public void setImageRoute(String imageRoute) {
        this.imageRoute = imageRoute;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(String endorsement) {
        this.endorsement = endorsement;
    }




}
