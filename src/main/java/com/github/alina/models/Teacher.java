package com.github.alina.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {

    private Set<Course> teachingCourses = new HashSet<>();


    public Teacher(String name, String userName, String password, int id) {
        super(name, userName, password, id);
    }
    public void  addTeachingCourses(Course course){
            teachingCourses.add(course);
    }

}


