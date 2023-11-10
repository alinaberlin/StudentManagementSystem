package com.github.alina.services;

import com.github.alina.models.Course;
import com.github.alina.models.Grade;
import com.github.alina.models.Student;

public class GradingService {

    public Grade createGrade(Student student, Course course, int grade) {
        if (student.getEnrolledCourses().contains(course)) {
            return new Grade(student, course, grade);
        }
        throw new RuntimeException("Student not enrolled");
    }
}
