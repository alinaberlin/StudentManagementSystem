package com.github.alina.services;

import com.github.alina.models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService extends AuthService<Student> {

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

    public Student update(Student student) {
        List<Student> students = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream("student.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Student existing = (Student) objectInputStream.readObject();
                if (student.getId() == existing.getId()) {
                    students.add(student);
                } else {
                    students.add(existing);
                }
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
        }
        for (Student s : students) {
            insert(s);
        }
        return student;
    }

    public void delete(int id) throws FileNotFoundException {
        List<Student> students = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("student.out");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Student existing = (Student) objectInputStream.readObject();
                if (existing.getId() != id) {
                    students.add(existing);
                }
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        students.forEach(s -> insert(s));
    }

    public Student findByName(String name) {
        try (
                FileInputStream fileInputStream = new FileInputStream("student.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                Student student = (Student) objectInputStream.readObject();
                if (student.getName().contains(name)) {
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



