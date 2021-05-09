package ru.steshko.springcourse.models;

import lombok.Data;


@Data
public class Role {
    private long id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }
}

