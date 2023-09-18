package ControllerClass;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import RestaurantDatabase.Food;
import RestaurantDatabase.Restaurant;
import RestaurantDatabase.RestaurantManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class restaurantSearch implements Initializable{

   //Properties for the fooddlist view of a restaurant
   @FXML
   private TableColumn<Food, String> categoryColumn;
   @FXML
    private TableColumn<Food, String> foodNameColumn;
    @FXML
    private TableColumn<Food, Double> foodPriceColumn;
    @FXML
    private TableView<Food> foodTableView=new TableView<>();
    //End of Foodlist view properties

    //Properties for Cartview
    @FXML
    private Button backButton;
     @FXML
    private TableView<Food> cartTableView;
    @FXML
    private TableColumn<Food, Double> cartPriceColumn;

    @FXML
    private TableColumn<Food, String> cartCategoryColumn;

    @FXML
    private TableColumn<Food, String> cartFoodNameColumn;

    @FXML
    private TableColumn<Food, Integer> cartFoodQuantityColumn;

    @FXML
    private Button cartPlaceOrderButton;

    //End of Cartview properties

    @FXML
    private Button proceedButton;
    @FXML
    private Button doneButton;
    @FXML
    private Button addToCartButton;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> restCombobox;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField upperPriceTextField;
    @FXML
    private TextField lowerPriceTextField;
    @FXML
    private TableView<Restaurant> restaurantTable;
    @FXML
    private TableColumn<Restaurant, Integer> idColumn;
    @FXML
    private TableColumn<Restaurant, String> nameColumn;
    @FXML
    private TableColumn<Restaurant, String> priceColumn;
    @FXML
    private TableColumn<Restaurant, Double> ratingsColumn;
    @FXML
    private TableColumn<Restaurant, Integer> zipCodeColumn;

    private Main main;
    private boolean foodDeliverStatus;
    private RestaurantManager restaurantManager=new RestaurantManager();
    private ArrayList<Restaurant> restaurantList=new ArrayList<>();
    private Stage stage;
    private ObservableList<String> list = FXCollections.observableArrayList("By Name","By Score","By Category","By Price","By Zipcode");

    public void setMain(Main main)
    {
      this.main=main;
    }


    public void setDeliverStatus(boolean status)
    {
      foodDeliverStatus=status;
    }

    public void init()
    {
     // makeFoodListViewFalse();
      makeMycartViewFalse();
      restaurantList=(ArrayList<Restaurant>) restaurantManager.getRestaurantList();
      showInTable(restaurantList);
    }

    public void setRestaurantManager(RestaurantManager restaurantManager)
    {
      this.restaurantManager=restaurantManager;
    }
    public void makeFalse()
    {
        searchTextField.setVisible(false);
        upperPriceTextField.setVisible(false);
        lowerPriceTextField.setVisible(false);
    }

    public void setNull()
    {
      searchTextField.setText(null);
      upperPriceTextField.setText(null);
      lowerPriceTextField.setText(null);
    }
    @FXML
   public void backClick(ActionEvent event) 
    {
      main.showCustomerHomePage();
    }

    
   public void logOutAction(ActionEvent event) 
    {
      main.showLoginPage();
    }

   int flag;
   public void searchBy(ActionEvent event) 
    {
         String selected = restCombobox.getValue();
         if(selected==list.get(0))
         {
            flag=10;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Restaurant Name--");
         }
         else if(selected.equals(list.get(1)))
         {
            flag=20;
            makeFalse();
            setNull();
            lowerPriceTextField.setVisible(true);
            upperPriceTextField.setVisible(true);
            lowerPriceTextField.setPromptText("Lower score--");
            upperPriceTextField.setPromptText("Upper score--");
         }
         else if(selected.equals(list.get(2)))
         {
            flag=11;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Category Name--");
         }
          else if(selected.equals(list.get(3)))
         {
            flag=12;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("Enter price--");
         }
          else if(selected.equals(list.get(4)))
         {
            flag=13;
            makeFalse();
            setNull();
            searchTextField.setVisible(true);
            searchTextField.setPromptText("ZipCode--");
         }
    }

    public void searchButtonClick()
    {
      if(flag==10)
      {
         String restName = searchTextField.getText();
         restaurantList=restaurantManager.searchRestaurantByName(restName);
         showInTable(restaurantList);
      }

      else if(flag==11)
      {
         String categoryName = searchTextField.getText();
         restaurantList = restaurantManager.searchRestaurantByCategory(categoryName);
         showInTable(restaurantList);
      }

      else if(flag==12)
      {
         String price = searchTextField.getText();
         restaurantList= restaurantManager.searchRestaurantByPrice(price);
         showInTable(restaurantList);
      }

      else if(flag==13)
      {
         String zipCode = searchTextField.getText();
         int zip =Integer.parseInt(zipCode);
         restaurantList=restaurantManager.searchRestaurantByZipCode(zip);
         showInTable(restaurantList);
      }

      else if(flag==20)
      {
         String lScore = lowerPriceTextField.getText();
         String uScore = upperPriceTextField.getText();
         double lowerScore = Double.parseDouble(lScore);
         double upperScore = Double.parseDouble(uScore);
         restaurantList = restaurantManager.searchRestaurantByScore(lowerScore, upperScore);
         showInTable(restaurantList);
      }
    }

   public void showInTable(ArrayList<Restaurant> restaurants)
   {
      if(restaurants==null)
      {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Not Found");
         alert.setHeaderText("No such restaurant in the databse");
         alert.showAndWait();
      }

      else
      {
         ObservableList<Restaurant> restList = FXCollections.observableArrayList(restaurants);
         idColumn.setCellValueFactory(new PropertyValueFactory<Restaurant,Integer>("Id"));
         nameColumn.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Name"));
         ratingsColumn.setCellValueFactory(new PropertyValueFactory<Restaurant,Double>("Score"));
         priceColumn.setCellValueFactory(new PropertyValueFactory<Restaurant,String>("price"));
         zipCodeColumn.setCellValueFactory(new PropertyValueFactory<Restaurant,Integer>("ZipCode"));

         restaurantTable.setItems(restList);
      }
   }

   HashMap<String,List<Food>> foodListOfrest=new HashMap<>();

   public void proceedButtonClick()
   {
      ObservableList<Restaurant> restList = restaurantTable.getSelectionModel().getSelectedItems();
      System.out.println(restList.get(0).getId());
      if(restList.size()!=0)
      {
         makeFalse();
         restaurantTable.setVisible(false);
         proceedButton.setVisible(false);
         searchButton.setVisible(false);
         restCombobox.setVisible(false);
         doneButton.setVisible(true);
         foodTableView.setVisible(true);
         addToCartButton.setVisible(true);
      }
      int restId=restList.get(0).getId();
      List<Food> fList = new ArrayList<>();
      fList = restaurantManager.getFoodList();
      System.out.println(fList.size());
      List<Food> listOfFood = new ArrayList<>();
      for(Food f: fList)
      {
         if(restId==f.getRestaurentId())
         {
            listOfFood.add(f);
         }
      }
      foodListOfrest.put(restList.get(0).getName(),listOfFood);
      System.out.println(listOfFood.size());
      showRestFoodList(listOfFood);
   }

   public void showRestFoodList(List<Food> f)
   {
      ObservableList<Food> restFoodList = FXCollections.observableArrayList(f);
      foodNameColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("Name"));
      categoryColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("category"));
      foodPriceColumn.setCellValueFactory(new PropertyValueFactory<Food,Double>("price"));

      foodTableView.setItems(restFoodList);
   }

   boolean isPlaceOrderClicked =false;

   ObservableList<Food> CartList = FXCollections.observableArrayList();
   List<Food> cartFoodList = new ArrayList<>();
   public void addTocart()
   {
     CartList = foodTableView.getSelectionModel().getSelectedItems();
     List<Food> f = new ArrayList<>(CartList);
     List<Food> tempFoodList = new ArrayList<>();
     if(isPlaceOrderClicked)
     {
       resetFoodOrderCount(f.get(0).getRestName());
       isPlaceOrderClicked=false;
     }
     f.get(0).increaseCount();
     if(cartFoodList.size()==0)
     {
      cartFoodList.add(f.get(0));
     }
     else
     {
      boolean flag=true;
       for(Food fd: cartFoodList)
       {
        if(fd.getName().equals(f.get(0).getName()))
         {
            flag=false;
            break;
         }
       }
       if(flag)
       {
         tempFoodList.add(f.get(0));
       }
     }

     System.out.println(tempFoodList.size());

     if(tempFoodList.size()!=0)
     {
      cartFoodList.add(tempFoodList.get(0));
      tempFoodList.clear();
     }
   }

   public void resetFoodOrderCount(String restName)
   {
      for(Food f: foodListOfrest.get(restName))
      {
         f.setCount();
      }
   }

   public void mycart()
   {
      makeFoodListViewFalse();
      makeRestViewFalse();
      makeCartViewTrue();

      ObservableList<Food> cartList= FXCollections.observableArrayList(cartFoodList);
      cartFoodNameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("Name"));
      cartCategoryColumn.setCellValueFactory(new PropertyValueFactory<Food, String>("category"));
      cartPriceColumn.setCellValueFactory(new PropertyValueFactory<Food,Double>("price"));
      cartFoodQuantityColumn.setCellValueFactory(new PropertyValueFactory<Food,Integer>("count"));

      cartTableView.setItems(cartList);
   }

   public void placeOrderButtonClick() throws IOException
   {
      isPlaceOrderClicked=true;
      if(cartFoodList.isEmpty())
      {
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Empty Cart");
         alert.setHeaderText("Your cart is Empty. Select Food to order");
         alert.showAndWait();
      }
      else
      {
         for(Food f: cartFoodList)
        {
         main.getNetworkUtil().write(f);
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Successfull!");
        alert.setHeaderText("Order placed successfully!!");
        alert.showAndWait();
        cartTableView.getItems().clear();
        cartFoodList.clear();
      }
   }

   public void showDeliveryConfirmAlert()
   {
      if(foodDeliverStatus)
      {
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Order Delivery");
         alert.setHeaderText("Enjoy  Your Food");
         alert.showAndWait();
      }
      else
      {
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Order Delivery");
         alert.setHeaderText("Your order is cooking");
         alert.showAndWait();
      }
   }

   public void goBackFromCart()
   {
      // if(isPlaced)
      // {
      //    cartTableView.getItems().clear();
      // }
      makeMycartViewFalse();
      restaurantTable.setVisible(true);
      proceedButton.setVisible(true);
      searchButton.setVisible(true);
      restCombobox.setVisible(true);
   }

   public void makeCartViewTrue()
   {
      cartPlaceOrderButton.setVisible(true);
      cartTableView.setVisible(true);
      backButton.setVisible(true);
   }

   public void makeFoodListViewFalse()
   {
      addToCartButton.setVisible(false);
      doneButton.setVisible(false);
      foodTableView.setVisible(false);
   }

   public void makeRestViewFalse()
   {
      proceedButton.setVisible(false);
      restCombobox.setVisible(false);
      searchButton.setVisible(false);
      makeFalse();
   }

   public void makeMycartViewFalse()
   {
      cartPlaceOrderButton.setVisible(false);
      cartTableView.setVisible(false);
       backButton.setVisible(false);
   }

   public void doneButtonClick()
   {
      makeFalse();
      restaurantTable.setVisible(true);
      proceedButton.setVisible(true);
      searchButton.setVisible(true);
      restCombobox.setVisible(true);
      doneButton.setVisible(false);
      foodTableView.setVisible(false);
      addToCartButton.setVisible(false);
   }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
       restCombobox.setItems(list);
    }


}
