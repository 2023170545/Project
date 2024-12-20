package com.mycompany.trafficmanagementsystem;

public class User {
    protected String ID;
    protected String name;
    protected String email;
    protected String password;
    protected String contactInfo;
    
    public User(){}

    public User(String ID, String name, String email, String password, String contactInfo) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    

}
