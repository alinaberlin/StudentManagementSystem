package com.github.alina.services;

import com.github.alina.models.User;

public class AuthService<T extends User> extends BaseService<T> {

    public T login(String userName, String password) {
        return elements.values().stream().filter(el ->
                        el.getUserName().equalsIgnoreCase(userName) && el.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public T register(String name, String userName, String password) {
        int id = elements.size() + 1;
        T user = (T) new User(name, userName, password, id);
        elements.put(id, user);
        return user;
    }
}
