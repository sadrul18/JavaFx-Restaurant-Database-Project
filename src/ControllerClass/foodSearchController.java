package ControllerClass;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import RestaurantDatabase.Food;
import RestaurantDatabase.RestaurantManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class foodSearchController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField1;
    @FXML
    private TextField priceRangeRest;
    @FXML
    private Label lable;
    @FXML
    private TableView<Food> table;
    @FXML
    private TableColumn<Food, String> foodNameColumn;
    @FXML
    private TableColumn<Food, String> RestaurantNameColumn;

    @FXML
    private TableColumn<Food, String> categoryColumn;
    @FXML
    private TableColumn<Food, Double> priceColumn;
    
    private RestaurantManager restaurantManager=new RestaurantManager();
    private Stage stage;
    ArrayList<Food> foods = new ArrayList<>();

    private Main main;
    
    private ObservableList<String> list = FXCollections.observableArrayList("By Name","By Name in Restaurent","By Category","By Category in Restaurant ","By Price Range","By Price Range in Restaurant","Costliest Food in Restaurant");

    public void init()
    {
        foods=(ArrayList<Food>)restaurantManager.getFoodList();
        showInTable(foods);
    }
    public void setMain(Main main)
    {
        this.main=main;
    }

    public void setRestaurantManager(RestaurantManager r)
    {
        restaurantManager=r;
    }
    public void makeFalse()
    {
        searchTextField.setVisible(false);
        textField2.setVisible(false);
        textField1.setVisible(false);
        priceRangeRest.setVisible(false);
    }

    public void setNull()
    {
        searchTextField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
        priceRangeRest.setText(null);
    }

    public void logOutClick(ActionEvent event) throws IOException
    {
        main.showLoginPage();
    }

     public void backClick(ActionEvent event) throws IOException
    {
        main.showCustomerHomePage();
    }

    int flag;
    public void searchBy(ActionEvent event)
    {
        String selected = comboBox.getValue();
        if(selected.equals(list.get(0)))
        {
            flag=10;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Food name--");
        }
        else if(selected.equals(list.get(1)))
        {
            flag=20;
            makeFalse();
            setNull();
            textField1.setVisible(true);
            textField2.setVisible(true);
            textField1.setPromptText("Food Name---");
            textField2.setPromptText("Rest. Name---");
        }
        else if(selected.equals(list.get(2)))
        {
            flag=11;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Category Name---");
        }
        else if(selected.equals(list.get(3)))
        {
            flag=21;
            makeFalse();
            setNull();
            textField1.setVisible(true);
            textField2.setVisible(true);
            textField1.setPromptText("Cat. Name---");
            textField2.setPromptText("Rest. Name--");
        }
        else if(selected.equals(list.get(4)))
        {
            flag=22;
            makeFalse();
            setNull();
            textField1.setVisible(true);
            textField2.setVisible(true);
            textField1.setPromptText("Lower price--");
            textField2.setPromptText("Upper price--");
        }
        else if(selected.equals(list.get(5)))
        {
            flag=30;
            makeFalse();
            setNull();
            textField1.setVisible(true);
            textField2.setVisible(true);
            textField1.setPromptText("Lower price--");
            textField2.setPromptText("Upper price--");
            priceRangeRest.setVisible(true);
        }
        else if(selected.equals(list.get(6)))
        {
            flag=12;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Rest. Name---");
        }
        // else if(selected.equals(list.get(7)))
        // {
        //     makeFalse();
        //     setNull();
        // }
    }

    public void searchButtonClick()
    {
       if(flag==10)
       {
            String name = searchTextField.getText();
            foods=restaurantManager.searchFoodByName(name.toLowerCase());
            showInTable(foods);
       }

       else if(flag==11)
       {
         String catName = searchTextField.getText();
         foods=restaurantManager.searchFoodByCategory(catName);
         showInTable(foods);
       }

       else if(flag==12)
       {
         String restName = searchTextField.getText();
         foods=restaurantManager.costliestFoodItem(restName);
         showInTable(foods);
       }

       else if(flag==20)
       {
          String foodName = textField1.getText();
          String restName = textField2.getText();
          foods=restaurantManager.searchFoodInReastaurant(restName, foodName);
          showInTable(foods);
       }

       else if(flag==21)
       {
         String catName=textField1.getText();
         String restName = textField2.getText();
         foods=restaurantManager.searchFoodByCategoryInRestaurant(restName,catName);
         showInTable(foods);
       }

       else if(flag==22)
       {
         String lowerPrice = textField1.getText();
         String upperPrice = textField2.getText();
         double lp=Double.parseDouble(lowerPrice);
         double up=Double.parseDouble(upperPrice);
         foods = restaurantManager.searchByPriceRange(lp, up);
         showInTable(foods);
       }

       else if(flag==30)
       {
         String lowerPrice = textField1.getText();
         String upperPrice = textField2.getText();
         String restName = priceRangeRest.getText();
         double lp = Double.parseDouble(lowerPrice);
         double up = Double.parseDouble(upperPrice);
         foods = restaurantManager.searchFoodByPriceRangeInRestaurant(lp, up, restName);
         showInTable(foods);
       }
    }

    public void showInTable(ArrayList<Food> foods)
    {
        
        if(foods==null)
        {
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("Not Found");
            alert.setHeaderText("Not Found in the database");
            alert.showAndWait();
        }
        else
        {
           ObservableList<Food> foodList = FXCollections.observableArrayList(foods);
           foodNameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("Name"));
           RestaurantNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("restName"));
           categoryColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("category"));
           priceColumn.setCellValueFactory(new PropertyValueFactory<Food,Double>("price"));

           table.setItems(foodList);
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        comboBox.setItems(list);
    }



}
