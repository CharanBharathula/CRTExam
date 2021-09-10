package com.example.crtexam;

public class Student {

    String email,password,name,rollno,mobile,branch;

    public Student(String email, String password, String name, String rollno, String mobile, String branch) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.rollno = rollno;
        this.mobile = mobile;
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRollno() {
        return rollno;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBranch() {
        return branch;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
