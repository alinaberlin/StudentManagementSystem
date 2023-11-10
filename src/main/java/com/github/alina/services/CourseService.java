package com.github.alina.services;

import com.github.alina.models.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    public void insert(Course course) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("courses.out");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(course);
            objectOutputStream.flush();
            System.out.println("Serialization complete");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Course find(int id) {
        try (
                FileInputStream fileInputStream = new FileInputStream("courses.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Course course = (Course) objectInputStream.readObject();
                if (course.getId() == id) {
                    return course;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Course not found");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Course update(Course course) {
        List<Course> courses = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream("courses.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Course existing = (Course) objectInputStream.readObject();
                if (course.getId() == existing.getId()) {
                    courses.add(course);
                } else {
                    courses.add(existing);
                }
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
        }
        for (Course s : courses) {
            insert(s);
        }
        return course;
    }

    public void delete(int id) throws FileNotFoundException {
        List<Course> courses = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("courses.out");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Course existing = (Course) objectInputStream.readObject();
                if (existing.getId() != id) {
                    courses.add(existing);
                }
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        courses.forEach(s -> insert(s));
    }
}
