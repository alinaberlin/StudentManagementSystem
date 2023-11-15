package com.github.alina.services;

import com.github.alina.models.Course;
import com.github.alina.models.Grade;
import com.github.alina.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradingServiceTest {
    private GradingService service;
    @BeforeEach
    void setUp() {
        service = new GradingService();
    }

    @Test
    void shouldGradeStudents() {
        Course course = new Course(2, "Math");
        Student student = new Student("Faruk","FarukStep", "134", 2);
        student.addCourse(course);
       Grade grade = service.createGrade(student, course, 7);
       assertEquals(7, grade.getGrade());

    }
    @Test
    void shouldNotGradeStudents() {
        Course course = new Course(2, "Math");
        Student student = new Student("Faruk","FarukStep", "134", 2);
       // Grade grade = service.createGrade(student, course, 7);
        assertThrows(RuntimeException.class, () -> service.createGrade(student, course, 7));

    }
}