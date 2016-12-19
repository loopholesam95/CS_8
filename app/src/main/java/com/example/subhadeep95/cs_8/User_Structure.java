package com.example.subhadeep95.cs_8;

/**
 * Created by Subhadeep95 on 12/18/2016.
 */

public class User_Structure {

    public String name,phone,email,pass;

    User_Structure() {
    }
    User_Structure(String name,String phone,String email,String pass) {
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.pass=pass;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setPhone(String phone)
    {
        this.phone=phone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPass(String pass)
    {
        this.pass=pass;
    }

    public String getPass()
    {
        return pass;
    }
}
