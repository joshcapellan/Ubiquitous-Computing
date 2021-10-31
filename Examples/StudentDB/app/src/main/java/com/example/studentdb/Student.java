package com.example.studentdb;

public class Student {
    private String _id;
    private String name;
    private String marks;

    public Student() {
    }

    public Student(String _id, String name, String marks) {
        this._id = _id;
        this.name = name;
        this.marks = marks;
    }
    public Student(String name, String marks) {
        this.name = name;
        this.marks = marks;
    }
    public Student(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

}
