package com.kis.mymessangerjava.Pojo;

public class User {

    private String id;
    private String name;
    private String lasetName;
    private String age;
    private Boolean isOnline;

    public User(String id, String name, String lasetName, String age, Boolean isOnline) {
        this.id = id;
        this.name = name;
        this.lasetName = lasetName;
        this.age = age;
        this.isOnline = isOnline;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLasetName(String lasetName) {
        this.lasetName = lasetName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLasetName() {
        return lasetName;
    }

    public String getAge() {
        return age;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }
}
