package ControllerClass;

import java.util.ArrayList;

import RestaurantDatabase.Restaurant;
import Server.FileOperation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class addRestaurantController {
    @FXML
    private TextField firstCategoryTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField restaurantIdTextField;

    @FXML
    private TextField restaurantNameTExtField;

    @FXML
    private TextField restaurantPriceTextField;

    @FXML
    private TextField restaurantScore;

    @FXML
    private TextField restaurantZipCodeTextField;

    @FXML
    private TextField secondCategoryTextField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField thirdCategory;

    private Main main;
    
    public void setMain(Main main)
    {
        this.main=main;
    }

    @FXML
    void backClick(ActionEvent event) 
    {
        main.showLoginPage();
    }

    @FXML
   public void submitButtonClick(ActionEvent event) 
    {
        String restName=null;
        String price=null;
        Double score=0.0;
        int zipcode=0;
        int restId=0;
        String password=null;
        ArrayList<String> categories =new ArrayList<>();

        restName = restaurantNameTExtField.getText();
        password= passwordTextField.getText();
        price = restaurantPriceTextField.getText();
        score =Double.parseDouble(restaurantScore.getText());
        zipcode = Integer.parseInt(restaurantZipCodeTextField.getText());
        restId = Integer.parseInt(restaurantIdTextField.getText());
        categories.add(firstCategoryTextField.getText());
        categories.add(secondCategoryTextField.getText());
        categories.add(thirdCategory.getText());

        if(restName == null || price == null || score==0.0|| restId ==0 || zipcode ==0 || password==null || categories.size()==0 )
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Incomplet incredentials");
            alert.setHeaderText("Enter all the requirements");
            alert.showAndWait();
        }

        else
        {
            Restaurant r = new Restaurant(password, restId, restName, score, price, zipcode, categories);

            FileOperation fileOperation = new FileOperation();
            fileOperation.appendRestaurant(r);
        }

        
    }
}