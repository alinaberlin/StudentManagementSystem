package com.github.alina.models;

import java.io.Serializable;
import java.util.Objects;

public class BaseModel implements Serializable {
    private int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return getId() == baseModel.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
