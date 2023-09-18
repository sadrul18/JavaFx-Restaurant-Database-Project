package ControllerClass;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class customerController {
    private Stage stage;
    private Main main;

    public void setMain(Main main)
    {
        this.main=main;
    }
    public void logOutClick(ActionEvent event) throws IOException
    {
        main.showLoginPage();
    }

    public void searchFoodClick(ActionEvent event) throws IOException
    {
        main.showFoodSearchPage();
    }

    public void searchRestaurantClick(ActionEvent event) throws IOException
    {
        main.showRestaurantSearchPage();
    }
    
}
