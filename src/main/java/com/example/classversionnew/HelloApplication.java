package com.example.classversionnew; //importing the package

import javafx.application.Application;  //importing the modules
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent loader = FXMLLoader.load(HelloApplication.class.getResource("Home.fxml"));   //launch the main page
        Scene scene = new Scene(loader);
        stage.setTitle("Foodies Fave Queue Management System"); //title for the popup GUI
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {    //launcher

            launch();
    }
}
