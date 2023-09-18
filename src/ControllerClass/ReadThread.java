package ControllerClass;

import javafx.application.Platform;
import Util.AdminLoginDTO;
import Util.DeliveryFood;
import Util.LoginDTO;
import Util.NetworkUtil;

import java.io.IOException;

import RestaurantDatabase.Food;
import RestaurantDatabase.RestaurantManager;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showCustomerHomePage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }

                    if(o instanceof AdminLoginDTO)
                    {
                        AdminLoginDTO adminLoginDTO = (AdminLoginDTO) o;
                        System.out.println(adminLoginDTO.getUserName());
                        System.out.println(adminLoginDTO.isStatus());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (adminLoginDTO.isStatus()) {
                                    try {
                                        main.restaurantPage(adminLoginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }

                    if(o instanceof RestaurantManager)
                    {
                        RestaurantManager restaurantManager = (RestaurantManager) o;
                        main.setRestaurantManager(restaurantManager);
                    }
                    if(o instanceof Food)
                    {
                        Food orderedFood = (Food) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                 {
                                    try 
                                    {
                                       main.setOrder(orderedFood); 
                                       main.restaurantPage(orderedFood.getRestName());
                                       System.out.println("Order received!!");
                                    } 
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }

                    if(o instanceof DeliveryFood)
                    {
                        DeliveryFood deliveryFood = (DeliveryFood) o;
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                 {
                                    try 
                                    {
                                        main.setDeliverFoodStatus(deliveryFood.getStatus());
                                       System.out.println("Order Delivered");
                                    } 
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



