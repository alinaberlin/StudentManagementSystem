package com.github.alina.models;

import java.util.Objects;

public class Grade extends BaseModel {
    private Student student;
    private Course course;
    private int grade;

    public Grade(Student student, Course course, int grade, int id) {
        super(id);
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(getStudent(), grade.getStudent()) && Objects.equals(getCourse(), grade.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse());
    }
}
