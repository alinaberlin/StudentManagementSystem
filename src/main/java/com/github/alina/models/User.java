package com.github.alina.models;

public class User extends BaseModel {
    private String name;
    private String userName;
    private String password;

    public User(String name, String userName, String password, int id) {
        super(id);
        this.name = name;
        this.userName = userName;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
