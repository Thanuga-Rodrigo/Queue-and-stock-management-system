package com.example.classversionnew;        //importing the package

import javafx.event.ActionEvent;        //importing the modules
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Page2Controller implements Initializable {     //creating class for the relevant fx page
    private Stage stage;
    private Scene scene;
    private Parent loader;

    @FXML
    private TextField tf1;      //assign variable to text field

    @FXML
    private ListView lv1;       //assign variable to list view

    private static FoodQueue cash1, cash2, cash3;   //importing the 3 queues
    private static WaitingList waiting;     //importing the waiting list

    public void switchToScene1(ActionEvent event) throws IOException {      //method for going back to customer display page
        loader = FXMLLoader.load(HelloApplication.class.getResource("GUI.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cash1 = FoodCenter.cashier_1;
        cash2 = FoodCenter.cashier_2;
        cash3 = FoodCenter.cashier_3;
        waiting = FoodCenter.waiting_list;



    }

    @FXML
    private void search(){      //making method for the search button
        lv1.getItems().clear();
        String name = tf1.getText();
        String newName;

        if (!name.equals("")) {
            newName = name.substring(0, 1).toUpperCase() + name.substring(1);

            for (Customer customer : cash1.getCashier()) {
                if (customer.getFirstName().contains(newName) && newName.charAt(0) == (customer.getFirstName().charAt(0))) {
                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 1 in position : " + String.valueOf(cash1.getCashier().indexOf(customer) + 1) + ")");
                }
            }
            for (Customer customer : cash2.getCashier()) {
                if (customer.getFirstName().contains(newName) && newName.charAt(0) == (customer.getFirstName().charAt(0))) {
                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 2 in position : " + String.valueOf(cash2.getCashier().indexOf(customer) + 1) + ")");
                }
            }
            for (Customer customer : cash3.getCashier()) {
                if (customer.getFirstName().contains(newName) && newName.charAt(0) == (customer.getFirstName().charAt(0))) {
                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 3 in position : " + String.valueOf(cash3.getCashier().indexOf(customer) + 1) + ")");
                }
            }
            for (Customer customer : waiting.getCashier()) {
                if (customer != null && customer.getFirstName().contains(newName) && newName.charAt(0) == (customer.getFirstName().charAt(0))) {
                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Waiting in position : " + String.valueOf(waiting.getCashier().indexOf(customer) + 1) + ")");
                }
            }
        }else {
            for (Customer customer : cash1.getCashier()) {

                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 1 in position : " + String.valueOf(cash1.getCashier().indexOf(customer) + 1) + ")");
            }
            for (Customer customer : cash2.getCashier()) {

                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 2 in position : " + String.valueOf(cash2.getCashier().indexOf(customer) + 1) + ")");

            }
            for (Customer customer : cash3.getCashier()) {

                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Cashier 3 in position : " + String.valueOf(cash3.getCashier().indexOf(customer) + 1) + ")");

            }
            for (Customer customer : waiting.getCashier()) {
                if (customer != null) {
                    lv1.getItems().add(customer.getFirstName() + " " + customer.getLastName() + "   (Waiting in position : " + String.valueOf(waiting.getCashier().indexOf(customer) + 1) + ")");
                }

            }
        }
    }
}
