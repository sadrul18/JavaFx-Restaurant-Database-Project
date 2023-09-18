package Server;

import Util.AdminLoginDTO;
import Util.DeliveryFood;
import Util.LoginDTO;
import Util.NetworkUtil;
import RestaurantDatabase.Food;
import RestaurantDatabase.RestaurantManager;
import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    RestaurantManager restaurantManager;
    public HashMap<String, String> userMap;
    private HashMap<String, String> restMap;
    private HashMap<String,NetworkUtil> restUtilMap = new HashMap<>();
    private HashMap<String,NetworkUtil> userUtilMap = new HashMap<>();


    public ReadThreadServer(HashMap<String, String> userMap,HashMap<String, String> restMap, HashMap<String,NetworkUtil> userUtilMap,HashMap<String,NetworkUtil> restUtilMap, NetworkUtil networkUtil,RestaurantManager restaurantManager) {
        this.userMap = userMap;
        this.restMap = restMap;
        this.restUtilMap=restUtilMap;
        this.networkUtil = networkUtil;
        this.restaurantManager=restaurantManager;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        userUtilMap.put(loginDTO.getUserName(),networkUtil);
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                        if(loginDTO.isStatus())
                        {
                            networkUtil.write(restaurantManager);
                        }
                        // else
                        // {
                        //     networkUtil.write(loginDTO);
                        // }
                    }

                    if(o instanceof AdminLoginDTO)
                    {
                        AdminLoginDTO adminLoginDTO = (AdminLoginDTO) o;
                        String password = restMap.get(adminLoginDTO.getUserName());
                        restUtilMap.put(adminLoginDTO.getUserName(),networkUtil);
                        adminLoginDTO.setStatus(adminLoginDTO.getPassword().equals(password));
                        networkUtil.write(adminLoginDTO);
                        if(adminLoginDTO.isStatus())
                        {
                            networkUtil.write(restaurantManager);
                        }
                    }

                    if(o instanceof Food)
                    {
                        Food food = (Food) o;
                        String restName=food.getRestName();
                        restUtilMap.get(restName).write(food);
                        System.out.println("Order passed throuegh ReadThreadServer");
                    }

                    if(o instanceof DeliveryFood)
                    {
                        DeliveryFood deliveryFood = (DeliveryFood) o;
                        String restName=deliveryFood.getRestName();
                        restUtilMap.get(restName).write(deliveryFood);
                        System.out.println("Delivery is ongoing!!");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



