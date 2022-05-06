package com.example.scenebuilderatm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public Button loginBtn;
    public Button checkBalanceBtn;
    public Button withdrawBtn;
    public Button withdraw2BTN;
    public Button depositBtn;
    public Button deposit2BTN;
    public Button exitBtn;
    public Label scene2PrompLbl;
    public Label withdrawLBL;
    public Label depositLBL;
    public TextField idField;
    public TextField passwordField;
    public TextField withdrawField;
    public TextField depositField;
    public Account[] actArr = new Account[10];
    public Stage stage;
    public Scene scene;
    public Parent root;
    public int id;
    public String password = "password";
    public boolean isBeginning = true;

    public void loginClicked(ActionEvent event) throws IOException {
        if (isBeginning == true) {
            for (int i = 0; i < 10; i++) {
                actArr[i] = new Account(i, 100);
            }
        }

        try {
            id = Integer.parseInt(idField.getText());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("You have to enter a number.");
            errorAlert.showAndWait();
            return;
        }
        String inputPassword = passwordField.getText();
        try {
            if (!inputPassword.equals(actArr[id].getPassword())) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Wrong password or id.");
                errorAlert.setContentText("You entered incorrect password or id number.");
                errorAlert.showAndWait();
                return;
            }
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid id");
            errorAlert.setContentText("Incorrect id.");
            errorAlert.showAndWait();
            return;
        }

        if (!(id >= 0) || !(id < 10)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Number has to be between 1 and 10.");
            errorAlert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        root = loader.load();
        Controller scene2controller = loader.getController();
        
        scene2controller.actArr = this.actArr;
        scene2controller.id = this.id;
        scene2controller.isBeginning = this.isBeginning;
        scene2controller.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
    public void checkBalanceBtnClicked() {
        double myBalance = actArr[id].balance;
        String myBalance2String = Double.toString(myBalance);
        scene2PrompLbl.setText("Balance: " + myBalance2String);
    }
    public void withdrawBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
        root = loader.load();
        Controller withdrawController = loader.getController();
        withdrawController.actArr = this.actArr;
        withdrawController.id = this.id;
        withdrawController.isBeginning = this.isBeginning;
        withdrawController.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void depositBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit.fxml"));
        root = loader.load();
        Controller depositController = loader.getController();
        depositController.actArr = this.actArr;
        depositController.id = this.id;
        depositController.isBeginning = this.isBeginning;
        depositController.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exitBtnClicked(ActionEvent event) throws IOException {
        isBeginning = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        Controller loginController = loader.getController();
        loginController.actArr = this.actArr;
        loginController.id = this.id;
        loginController.isBeginning = this.isBeginning;
        loginController.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void withdraw2BTNClicked(ActionEvent event) throws IOException {
        double amount;
        String stringAmount = withdrawField.getText();
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (Exception e) {
            withdrawLBL.setText("You entered a non number.");
            return;
        }
        if (amount > actArr[id].balance) {
            withdrawLBL.setText("You do not have enough in your account.");
            return;
        }
        if (amount < 0) {
            withdrawLBL.setText("You can't withdraw a negative amount.");
            return;
        }
        actArr[id].balance -= amount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        root = loader.load();
        Controller mainMenuController = loader.getController();
        mainMenuController.actArr = this.actArr;
        mainMenuController.id = this.id;
        mainMenuController.isBeginning = this.isBeginning;
        mainMenuController.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void deposit2BTNClicked(ActionEvent event) throws IOException {
        double amount;
        String stringAmount = depositField.getText();
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (Exception e) {
            depositLBL.setText("You entered a non number.");
            return;
        }
        if (amount < 0) {
            depositLBL.setText("You can't deposit a negative amount.");
            return;
        }
        actArr[id].balance += amount;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        root = loader.load();
        Controller mainMenuController = loader.getController();
        mainMenuController.actArr = this.actArr;
        mainMenuController.id = this.id;
        mainMenuController.isBeginning = this.isBeginning;
        mainMenuController.password = this.password;
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}