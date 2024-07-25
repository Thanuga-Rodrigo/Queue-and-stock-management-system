package com.example.classversionnew; //importing the packages


import javafx.event.ActionEvent;        //importing the required modules
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label lb1, lb2, lb3, lb4;   //appends labels into fxids

    @FXML
    private VBox box1, box2, box3;      //appends VBoxes into fxids

    private Stage stage;
    private Scene scene;
    private Parent loader;

    public void switchToScene2(ActionEvent event) throws IOException {  //method to use on onAction
        loader = FXMLLoader.load(HelloApplication.class.getResource("Page2.fxml")); //calling the search page
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FoodQueue cash1 = FoodCenter.cashier_1;
        FoodQueue cash2 = FoodCenter.cashier_2;
        FoodQueue cash3 = FoodCenter.cashier_3;
        int burgers = FoodCenter.currentStock;


        for (int i = 0; i < cash1.getCashier().size(); i++ ){
            box1.getChildren().get(i).setVisible(true); //making the images visible when customer is added

            int finalI = i;
            box1.getChildren().get(i).setOnMouseClicked(mouseEvent -> {     //once clicked on the image shows data of the customer for cashier1
                lb1.setText(cash1.getCashier().get(finalI).getFirstName());
                lb2.setText(cash1.getCashier().get(finalI).getLastName());
                lb3.setText(String.valueOf(cash1.getCashier().get(finalI).getBurgersRequired()));
            });

        }
        for (int i = 0; i < cash2.getCashier().size(); i++ ){       //once clicked on the image shows data of the customer for cashier2
            box2.getChildren().get(i).setVisible(true);

            int finalI = i;
            box2.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                lb1.setText(cash2.getCashier().get(finalI).getFirstName());
                lb2.setText(cash2.getCashier().get(finalI).getLastName());
                lb3.setText(String.valueOf(cash2.getCashier().get(finalI).getBurgersRequired()));
            });
        }

        for (int i = 0; i < cash3.getCashier().size(); i++ ){   //once clicked on the image shows data of the customer for cashier3
            box3.getChildren().get(i).setVisible(true);

            int finalI = i;
            box3.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                lb1.setText(cash3.getCashier().get(finalI).getFirstName());
                lb2.setText(cash3.getCashier().get(finalI).getLastName());
                lb3.setText(String.valueOf(cash3.getCashier().get(finalI).getBurgersRequired()));
            });
        }

        lb4.setText(String.valueOf(burgers));   //getting the amount of burgers in stock
    }

    
}