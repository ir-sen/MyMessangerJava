package com.kis.mymessangerjava.Pojo;

public class User {

    private String id;
    private String name;
    private String lasetName;
    private int age;
    private Boolean isOnline;

    public User(String id, String name, String lasetName, int age, Boolean isOnline) {
        this.id = id;
        this.name = name;
        this.lasetName = lasetName;
        this.age = age;
        this.isOnline = isOnline;
    }

    public User() {
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

    public void setAge(int age) {
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

    public int getAge() {
        return age;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lasetName='" + lasetName + '\'' +
                ", age=" + age +
                ", isOnline=" + isOnline +
                '}';
    }
}
