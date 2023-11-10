package com.github.alina.services;

import com.github.alina.models.Student;

import java.io.*;

public class StudentService {

    public StudentService() {
    }

    public void insert(Student student) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("student.out");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(student);
            objectOutputStream.flush();
            System.out.println("Serialization complete");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Student find(int id) {
        try (
                FileInputStream fileInputStream = new FileInputStream("student.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Student student = (Student) objectInputStream.readObject();
                if (student.getId() == id) {
                    return student;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Student not found");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



