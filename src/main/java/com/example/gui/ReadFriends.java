package com.example.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ReadFriends {
    private static String name;
    private static int age;
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friends> friends = new ArrayList<>();

    /**
     * Read all the friends from a group from in its txt file
     * @param filename the name of the txt file
     * @return An arraylist of all the friends object in the txt file.
     * @throws IOException
     */
    public static ArrayList displayAllFriends(String filename) throws IOException {
        fr = new FileReader(filename);
        br = new BufferedReader(fr);
        friends.clear();
        String line;
        String friendString = "";
        while((line = br.readLine()) != null){
            if(!line.equals(";")){
                friendString += line;
            }else{
                parseFriends(friendString);
                //System.out.println(friendString);
                friendString = "";
            }
        }

        return friends;
    }

    /**
     * Change a string into a friends object
     * @param n the string of information relating to a friend
     */
    public static void parseFriends(String n){
        int pos = 0;
        String name = "";
        int age = 0;
        String phone = "";
        //Separate the friend's name, age, and phone number
        for(int i = 0; i <n.length(); i++){
            if(n.substring(i,i+1).equals(",")){
                pos = i;
                name = n.substring(0,pos);
            }else if(n.substring(i,i+1).equals(";")){
                age = Integer.parseInt(n.substring(pos+1, i));
                phone = n.substring(i+1);
            }
        }
        Friends temp = new Friends(name,age,phone);
        friends.add(temp);
        //Check if the friend already exists.
        for(int i = 0; i < friends.size()-1;i++){
            Friends f = friends.get(i);
            if(temp.compareFriends(f)){
                friends.remove(i);
            }
        }
    }
}
