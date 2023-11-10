package com.github.alina.services;

import com.github.alina.models.Teacher;

import java.io.*;

public class TeacherService {
    public TeacherService() {
    }

    public void insert(Teacher teacher) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("teacher.out");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(teacher);
            objectOutputStream.flush();
            System.out.println("Serialization complete");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Teacher find(int id) {
        try (FileInputStream fileInputStream = new FileInputStream("teacher.out");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Teacher teacher = (Teacher) objectInputStream.readObject();
                if (teacher.getId() == id) {
                    return teacher;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Teacher not found");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

