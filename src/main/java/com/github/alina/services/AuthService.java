package com.github.alina.services;

import com.github.alina.models.User;

import java.io.*;

public class AuthService<T extends User> {
    public T login(String userName, String password) {
        try (
                FileInputStream fileInputStream = new FileInputStream("student.out");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                T student = (T) objectInputStream.readObject();
                if (student.getUserName().equals(userName) && student.getPassword().equals(password)) {
                    return student;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("User not found");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public T register(String name, String userName, String password) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("student.out");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                T user = (T) objectInputStream.readObject();
                if (user.getUserName().equals(userName)) {
                    return user;
                }
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("student.out");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            T user = (T) new User(name, userName, password, 1);
            objectOutputStream.writeObject(user);
            return user;
        }
    }
}
