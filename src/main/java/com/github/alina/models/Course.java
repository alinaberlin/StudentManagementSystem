package com.github.alina.models;

public class Course extends BaseModel {

    private String name;

    public Course(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                super.toString() + "} ";
    }
}

