package com.example.lab3;

public class List {
    private String _id;
    private String name;
    private String list;

    public List() {
    }

    public List(String _id, String name, String list) {
        this._id = _id;
        this.name = name;
        this.list = list;
    }
    public List(String name, String list) {
        this.name = name;
        this.list = list;
    }
    public List(String _id) { this._id = _id; }

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

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

}
