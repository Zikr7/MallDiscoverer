package com.example.mall;

public class Contact {

    private int id;
    private String email;
    private String name;
    private String subject;
    private String message;

    public Contact() {
        // Default constructor
    }

    public Contact(int id, String email, String name, String subject, String message) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.message = message;
    }

    // Getters

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
