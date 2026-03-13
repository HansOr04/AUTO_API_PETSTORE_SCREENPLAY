package com.automation.model;

public class Pet {

    private long id;
    private String name;
    private String status;

    public Pet() {}

    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public static Pet withName(String name, String status) {
        return new Pet(0, name, status);
    }

    public static Pet withIdAndName(long id, String name, String status) {
        return new Pet(id, name, status);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
