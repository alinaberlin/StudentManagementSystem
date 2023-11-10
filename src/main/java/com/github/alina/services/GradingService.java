package com.github.alina.services;

import com.github.alina.models.Course;
import com.github.alina.models.Grade;
import com.github.alina.models.Student;
import com.github.alina.models.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GradingService {

    public Grade createGrade(Student student, Course course, int grade) {
        if (student.getEnrolledCourses().contains(course)) {
            return new Grade(student, course, grade);
        }
        throw new RuntimeException("Student not enrolled");
    }

    public void insert(Grade grade) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("grades.out");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(grade);
            objectOutputStream.flush();
            System.out.println("Serialization complete");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Grade update(Grade grade) {
        List<Grade> grades = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream("grades.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Grade existing = (Grade) objectInputStream.readObject();
                if (grade.equals(existing)) {
                    grades.add(grade);
                } else {
                    grades.add(existing);
                }
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }

        grades.forEach(t -> insert(t));
        return grade;
    }
}
