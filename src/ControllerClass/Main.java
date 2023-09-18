package ControllerClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import RestaurantDatabase.Food;
import RestaurantDatabase.RestaurantManager;
import Util.NetworkUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application {
    private NetworkUtil networkUtil;
    private Stage stage;
    private List<Food> orderedList = new ArrayList<>();
    private boolean deliverStatus;
    restaurantSearch rSearch=new restaurantSearch();
    private RestaurantManager restaurantManager=new RestaurantManager();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException
    {
        stage = new Stage();
        showLoginPage();
        connectToServer();
    }

     private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public NetworkUtil getNetworkUtil()
    {
        return networkUtil;
    }

    public void setRestaurantManager(RestaurantManager r)
    {
        restaurantManager=r;
    }

    public void setDeliverFoodStatus(boolean status)
    {
        deliverStatus=status;
        rSearch.setDeliverStatus(status);
        rSearch.showDeliveryConfirmAlert();
    }

    public void setOrder(Food orderedFood)
    {

        if(orderedList.size()==0)
        {
            orderedList.add(orderedFood);
        }
        else
        {
            boolean flag=true;
            int index=0;
           for(int i=0;i<orderedList.size();i++)
           {
              Food f=orderedList.get(i);
              if(f.getName().equals(orderedFood.getName()))
              {
                flag=false;
                index=i;
                break;
              }
           }

           if(flag)
           {
            orderedList.add(orderedFood);
           }
           else
           {
            orderedList.get(index).addCount(orderedFood.getCount());
           }
        }

        
    }

    public void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            loginController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAdminInputPage()
    {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/adminInput.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            adminLoginController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showCustomerInputPage(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/customerInput.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            inputController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showCustomerHomePage() {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/customerOption.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            customerController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void restaurantPage(String userName)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/restaurent.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Restaurent controller = loader.getController();
            controller.setRestaurantManager(restaurantManager);
            controller.setUserName(userName);
            controller.setOrderedFood(orderedList);
            controller.pendingOrderButtonClick();
            controller.init();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showFoodSearchPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/food.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            foodSearchController controller = loader.getController();
            controller.setRestaurantManager(restaurantManager);
            controller.init();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showRestaurantSearchPage()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/searchRestaurant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            restaurantSearch controller = loader.getController();
            controller.setRestaurantManager(restaurantManager);
            controller.init();
            controller.setDeliverStatus(deliverStatus);
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAddRestaurant()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FxmlFile/addRestaurant.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            addRestaurantController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showAlert()
    {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Wrong Incredentials!!");
        alert.setHeaderText("Invalid username or password");
        System.out.println("Invalid username or password");
        alert.showAndWait();
    }

}