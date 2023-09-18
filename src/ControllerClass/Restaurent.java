package ControllerClass;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import RestaurantDatabase.Food;
import RestaurantDatabase.Restaurant;
import RestaurantDatabase.RestaurantManager;
import Util.DeliveryFood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Restaurent implements Initializable{

    //Properties for the Foodlist table view
    @FXML
    private TableView<Food> foodListTableView;
    @FXML
    private TableColumn<Food, String> CategoryColumn;
    @FXML
    private TableColumn<Food, String> foodNameColumn;
    @FXML
    private TableColumn<Food, Double> priceColumn;
    //Properties for the ordered-table view
    @FXML
    private TableView<Food> pendingOrderTable;
    @FXML
    private TableColumn<Food, String> orderedCategoryColumn;

    @FXML
    private TableColumn<Food, String> orderedFoodNameColumn;

    @FXML
    private TableColumn<Food, Integer> orderedquantityColumn;

    //Buttons
    @FXML
    private Button deliveerButton;
    @FXML
    private Button addFoodButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button pendingOrderButton;

     @FXML
    private ListView<String> restaurantListView;
    @FXML
    private Label detailsLabel;
    @FXML
    private Label restaurantNameLabel;
    @FXML
    private Label welcomeLabel;

    Stage stage;
    String userName;
   
    private Main main;
    private RestaurantManager restaurantManager = new RestaurantManager();
    private List<Restaurant> restaurantsList=new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private List<Food> foodListOfThis = new ArrayList<>();
    private List<Food> orderedFoods =new ArrayList<>();

    Restaurant restaurant=new Restaurant();
    private ArrayList<String> list = new ArrayList<String>();

    public void setMain(Main main)
    {
        this.main=main;
    }

    public void setRestaurantManager(RestaurantManager restaurantManager)
    {
        this.restaurantManager=restaurantManager;
    }

    public void setOrderedFood(List<Food> ordered)
    {
        this.orderedFoods=ordered;
    }

    public void setUserName(String userName)
    {
        this.userName=userName;
        System.out.println(userName);
    }

    public void init()
    {
       // restaurantNameLabel.setText(userName);
        restaurantsList=restaurantManager.getRestaurantList();
        foodList=restaurantManager.getFoodList();
        for(Restaurant r: restaurantsList)
        {
            if(userName.equalsIgnoreCase(r.getName()))
            {
                restaurant=r;
            }
        }

        for(Food f: foodList)
        {
            if(restaurant.getId()==f.getRestaurentId())
            {
                foodListOfThis.add(f);
            }
        }

        list.add("Restaurent ID: " + restaurant.getId());
        list.add("Categories: ");
        list.addAll(restaurant.getCategories());
        list.add("Price: " + restaurant.getPrice());
        list.add("Zip-Code: " + restaurant.getZipCode());
        list.add("Total Food Items: " + foodListOfThis.size());
    }

    boolean flag=true;
    public void showInListView(List<String> list)
    {
        flag = false;
        restaurantListView.getItems().addAll(list);
    }

    public void showMenuList(ActionEvent event)
    {
        foodListTableView.setVisible(true);
        welcomeLabel.setText("Your menu list: ");
        welcomeLabel.setVisible(true);
        restaurantNameLabel.setVisible(false);
        detailsLabel.setVisible(false);
        restaurantListView.setVisible(false);
        pendingOrderTable.setVisible(false);
        deliveerButton.setVisible(false);
        ObservableList<Food> list = FXCollections.observableArrayList(foodListOfThis);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("Name"));;
        priceColumn.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("category"));

        foodListTableView.setItems(list);
    }

    public void homeButtonClick()
    {
        welcomeLabel.setText("Wellcome to the Food World of ");
        restaurantNameLabel.setText(userName);
        welcomeLabel.setVisible(true);
        restaurantNameLabel.setVisible(true);
        detailsLabel.setVisible(true);
        restaurantListView.setVisible(true);
        foodListTableView.setVisible(false);
        pendingOrderTable.setVisible(false);
        deliveerButton.setVisible(false);
        if(flag)
        {
            showInListView(list);
        }
    }

    public void pendingOrderButtonClick()
    {
        deliveerButton.setVisible(true);
        welcomeLabel.setVisible(false);
        restaurantNameLabel.setVisible(false);
        detailsLabel.setVisible(false);
        restaurantListView.setVisible(false);
        foodListTableView.setVisible(false);
        pendingOrderTable.setVisible(true);
        ObservableList<Food> orders = FXCollections.observableArrayList(orderedFoods);
        orderedFoodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("Name"));
        orderedCategoryColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("category"));
        orderedquantityColumn.setCellValueFactory(new PropertyValueFactory<Food,Integer>("count"));

        pendingOrderTable.setItems(orders);
    }

    public void refreshButtonClick()
    {
        main.restaurantPage(userName);
    }

    public void deliverButtonClick() throws IOException
    {
        DeliveryFood deliverFood = new DeliveryFood();
        deliverFood.setStatus(true);
        deliverFood.setRestaurantName(userName);
        main.getNetworkUtil().write(deliverFood);
        pendingOrderTable.getItems().clear();
        orderedFoods.clear();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Order Delivery");
        alert.setHeaderText("Orders have been deliverd!!");
    }

    public void logOutClick(ActionEvent event) throws IOException
    {
        main.showLoginPage();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
       // restaurantListView.getItems().addAll(list);
    } 

}
