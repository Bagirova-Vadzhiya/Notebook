package com.example.notebook.model;

public class Notebook {
    // поля
    private String id;
    private String title;
    private String description;

    // конструктор
    public Notebook(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
