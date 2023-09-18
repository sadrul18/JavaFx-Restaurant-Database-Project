package ControllerClass;

import java.io.IOException;

import Util.AdminLoginDTO;
import Util.LoginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class adminLoginController {

     @FXML
    private TextField adminUserNameTextField;
    @FXML
    private PasswordField adminPasswordField;
    private Stage stage;
    private Main main;
    Restaurent restaurent = new Restaurent();

    public void setMain(Main main)
    {
        this.main=main;
    }
    public void adminLogin(ActionEvent event) throws IOException
    {
        String name=adminUserNameTextField.getText();
        String password = adminPasswordField.getText();
        restaurent.setUserName(name);
        AdminLoginDTO adminLoginDTO=new AdminLoginDTO();
        adminLoginDTO.setUserName(name);
        adminLoginDTO.setPassword(password);
        main.getNetworkUtil().write(adminLoginDTO);
    }

    public void backClick(ActionEvent event) throws IOException
    {
        main.showLoginPage();
    }

    public void resetAction(ActionEvent event)
    {
        adminUserNameTextField.setText(null);
        adminPasswordField.setText(null);
    }
    
}
