package ControllerClass;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {
    @FXML
    private Button adminLogin;
    @FXML
    private Button customerLogin;
    @FXML
    private AnchorPane loginPane;

    private Stage stage;
    private Main main;


    public void adminLoginClick(ActionEvent event) throws IOException
    {
        main.showAdminInputPage();
    }

    public void setMain(Main main){
        this.main = main;
    }

    public void customerLoginClick(ActionEvent event) throws IOException
    {
       main.showCustomerInputPage();
    }

    @FXML
    void showAddRestPage(ActionEvent event) 
    {
        main.showAddRestaurant();
    }
}
