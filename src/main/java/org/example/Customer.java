package org.example;

/*
 * Project: Lab3 Database Assignment
 * Purpose Details: Represents a customer entity.
 * Course: IST 242
 * Author: Aayudh Nandiwdekar
 * Date Developed: 10/08/2024
 * Last Date Changed:
 * Revision:
 */

public class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
