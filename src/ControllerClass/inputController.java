package ControllerClass;

import java.io.IOException;

import Util.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class inputController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
     @FXML
    private TextField adminUserNameTextField;
    @FXML
    private PasswordField adminPasswordField;
    @FXML
    private AnchorPane customerPane;
    @FXML
    private AnchorPane adminPane;

    private Stage stage;
    
    private Main main;

     public void setMain(Main main){
        this.main = main;
    }


    public void customerLogin(ActionEvent event) throws IOException
    {
        String name=userNameTextField.getText();
        String password = passwordField.getText();
        LoginDTO loginDTO =new LoginDTO();
        loginDTO.setUserName(name);
        loginDTO.setPassword(password);
        main.getNetworkUtil().write(loginDTO);
    }
    
    public void backClick(ActionEvent event) throws IOException
    {
        main.showLoginPage();
    }

    public void resetAction(ActionEvent event)
    {
        userNameTextField.setText(null);
        passwordField.setText(null);
    }

    
}
