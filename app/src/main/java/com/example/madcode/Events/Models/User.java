package com.example.madcode.Events.Models;

public class User {
    public String uname,password,email,phone,dob,uri;

    public User(){}

    public User(String uname, String username, String password, String email, String phone, String dob,String uri) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
