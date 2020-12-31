package com.example.itjobs.Model;

public class Data {

    String Title;
    String Des;
    String Skill;
    String Salary;

    String id;
    String Data;

    public Data(){

    }

    public Data(String title, String des, String skill, String salary, String id, String data) {
        Title = title;
        Des = des;
        Skill = skill;
        Salary = salary;
        this.id = id;
        Data = data;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
