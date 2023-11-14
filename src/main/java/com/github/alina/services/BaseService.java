package com.github.alina.services;

import com.github.alina.models.BaseModel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BaseService<T extends BaseModel> {
    Map<Integer, T> elements = new HashMap<>();

    public boolean insert(T element) {
        if (elements.containsKey(element.getId())) {
            return false;
        }
        elements.put(element.getId(), element);
        return true;
    }

    public boolean update(T element) {
        if (!elements.containsKey(element.getId())) {
            return false;
        }
        elements.put(element.getId(), element);
        return true;
    }

    public T find(int id) {
        return elements.get(id);
    }

    public T delete(int id) {
        return elements.remove(id);
    }

    public void loadFromFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            while (true) {
                T element = (T) objectInputStream.readObject();
                elements.put(element.getId(), element);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFile(String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            elements.values().forEach(el -> {
                try {
                    objectOutputStream.writeObject(el);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void printAll() {
        elements.values().forEach(System.out::println);
    }

}
