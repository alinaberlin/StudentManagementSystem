package com.github.alina.models;

import java.util.Objects;

public class Grade {
    private Student student;
    private Course course;
    private int grade;

    public Grade(Student student, Course course, int grade) {
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
        Grade grade1 = (Grade) o;
        return getGrade() == grade1.getGrade() && Objects.equals(getStudent(), grade1.getStudent()) && Objects.equals(getCourse(), grade1.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse(), getGrade());
    }
}
