package com.example.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    //Fields
    public TextField textGetName;
    public TextField textGetAge;
    public TextField textGetPhone;
    public ListView friendList = new ListView<>();
    public Label lblName;
    public Label lblAge;
    public Label lblPhone;
    public TextField textFileName;

    /**
     * Add a friend to the list of friends
     * @param actionEvent By clicking the Add Friend Button
     */
    public void addFriend (ActionEvent actionEvent){
        String name = textGetName.getText();
        int age = Integer.parseInt(textGetAge.getText());
        String phone = textGetPhone.getText();
        Friends temp = new Friends(name, age, phone);
        friendList.getItems().add(temp);
        textGetName.clear();
        textGetAge.clear();
        textGetPhone.clear();
    }


    /**
     * Display the selected friend with his/her information
     * @param mouseEvent By selecting a friend in the friendList
     */
    public void displayFriend(MouseEvent mouseEvent) {
        Friends temp;
        temp = (Friends) friendList.getSelectionModel().getSelectedItem();
        lblName.setText(temp.name);
        lblAge.setText(Integer.toString(temp.getAge()));
        lblPhone.setText(temp.getPhone());
    }


    /**
     * Save the list of friends to a group and creating a txt file to store all the information
     * @param actionEvent By clicking the Save All Friend button
     * @throws IOException
     */
    public void saveFriend(ActionEvent actionEvent) throws IOException {
        ObservableList<Friends> list = friendList.getItems();
        String fileName = textFileName.getText();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).compareFriends(list.get(j))) {
                    list.remove(j);
                }
            }
        }
        for (Friends f: list){
            f.addToFile(fileName);
        }
        friendList.getItems().clear();
    }

    /**
     * Display all the friends in a group by reading the group's txt file
     * @param actionEvent By clicking the Load All Friends button
     * @throws IOException
     */
    public void loadFriend(ActionEvent actionEvent) throws IOException {
        friendList.getItems().clear();
        String fileName = textFileName.getText();
        ArrayList<Friends> friends = ReadFriends.displayAllFriends(fileName+".txt");
        for (Friends f: friends){
            friendList.getItems().add(f);
        }
    }

    /**
     * Clear all the friends in the list
     * @param actionEvent By clicking the clear list button
     */
    public void clearList(ActionEvent actionEvent) {
        friendList.getItems().clear();
    }
}