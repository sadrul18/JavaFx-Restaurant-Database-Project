package Util;

import java.io.Serializable;

public class DeliveryFood implements Serializable
{
    public String restaurantName;
    boolean isStatus;
     public void setStatus(boolean flag)
     {
        isStatus=flag;
     }

     public void setRestaurantName(String name)
     {
        restaurantName=name;
     }

     public boolean getStatus()
     {
        return isStatus;
     }

     public String getRestName()
     {
        return restaurantName;
     }
}