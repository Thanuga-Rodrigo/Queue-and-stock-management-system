package com.example.classversionnew;    //importing packages

import javafx.event.ActionEvent;        //importing the modules
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage {     //class for home page

    private Stage stage;
    private Scene scene;
    private Parent loader;
    public void ViewCustomers(ActionEvent event) throws IOException {       //onAction for switching on pages
        loader = FXMLLoader.load(HelloApplication.class.getResource("GUI.fxml"));   //opening the customer display page
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

}
