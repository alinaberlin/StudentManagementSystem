package com.github.alina.services;

import com.github.alina.models.Course;
import com.github.alina.models.Grade;
import com.github.alina.models.Student;

public class GradingService extends BaseService<Grade> {

    public Grade createGrade(Student student, Course course, int grade) {
        int id = elements.size() + 1;
        if (student.getEnrolledCourses().contains(course)) {
            return new Grade(student, course, grade, id);
        }
        throw new RuntimeException("Student not enrolled");
    }

}
