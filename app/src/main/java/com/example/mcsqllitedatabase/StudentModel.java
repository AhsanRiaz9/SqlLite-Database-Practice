package com.example.mcsqllitedatabase;

public class StudentModel {
    private int id;
    private String name;
    private float cgpa;
    public StudentModel(String name, float cgpa)
    {
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "name='" + name + '\'' +
                ", cgpa=" + cgpa +
                '}';
    }
}
