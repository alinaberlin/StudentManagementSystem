package com.github.alina.models;

import java.util.HashSet;
import java.util.Set;

public class Student extends User {
    private Set<Course> enrolledCourses = new HashSet<>();

    public Student(String name, String userName, String password, int id) {
        super(name, userName, password, id);
    }
    public Set<Course> getEnrolledCourses(){
        return enrolledCourses;
    }
    public void addCourse(Course course) {
            enrolledCourses.add(course);

    }
}
