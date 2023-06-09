package com.example.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Friends {
    //Fields
    public String name;
    private int age;
    private String phone;

    //Constructor
    public Friends(String name, int age, String phone){
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    //Setter and Getter
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public java.lang.String toString() {
        return name;
    }

    /**
     * Add the friends object to the indicated file
     * @param fileName the name of the file
     * @throws IOException
     */
    public void addToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName + ".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name + ",\r" + Integer.toString(age) + ";\r" + phone+ "\r");
        bw.write(";\r");
        bw.close();
    }

    /**
     * Compare whether two friends objects are the same.
     * @param f A friends object to compare with.
     * @return True if objects are the same, false if objects are not the same.
     */
    public boolean compareFriends(Friends f){
        if(this.phone.equals(f.phone)){
            return true;
        }else{
            return false;
        }
    }
}
