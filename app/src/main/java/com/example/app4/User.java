package com.example.app4;

public class User {
    private String Name;
    private Integer Contactnumber;
    private String Email;
    private String Slot;

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getContactnumber() {
        return Contactnumber;
    }

    public void setContactnumber(Integer contactnumber) {
        Contactnumber = contactnumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }
}
