package com.scheduling.scheduler.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //It tells about this class that it's a table
public class Employee {

    @Id //marks it as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //lets the database auto-generate the id number
    private Long id;

    private String name;

    private String email;

    private String role;

    // as an entity, no needs to initialize any variables here.
    //They can be set or get only through these methods by the outer classes.

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
