package com.example.addisu;
import java.io.Serializable;

public class Contact implements Serializable { private static final long serialVersionUID = 10; private String fName;

    private String lName;
    private String Phone;

    private int contactId;

    private String email;

    public Contact(String fName, String lName, String phone, int contactId, String email) {

        this.fName = fName;
        this.lName = lName;

        this.Phone = phone;
        this.contactId = contactId;
        this.email = email;
    }

    public Contact(){}

    public int getContactId() { return contactId; }

    public void setContactId(int contactId){this.contactId = contactId;}

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) { this.fName = fName;
    }

    public String getlName() {

        return lName;
    }

    public void setlName(String lName) { this.lName = lName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) { Phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

}
