package RestaurantDatabase;

import java.io.Serializable;

public class Food implements Serializable
{
    private int restaurentId;
    private String category;
    private String Name;
    private String restName;
    private double price;
    private int count;
    public Food()
    {

    }
    public Food(int restaurentId,String restName, String category, String Name, double price) {
        this.restaurentId = restaurentId;
        this.category = category;
        this.Name = Name;
        this.restName=restName;
        this.price = price;
        count=0;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRestName(String restName)
    {
        this.restName=restName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount()
    {
        count=0;
    }

    public void addCount(int x)
    {
        count=count+x;
    }

    public void increaseCount()
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return Name;
    }

    public String getRestName()
    {
        return restName;
    }

    public double getPrice() {
        return price;
    }

    // void showDetails()
    // {
    //   System.out.println();
    //   System.out.println("Restaurent Id: " + restaurentId);
    //   System.out.println("Food category: " + category);
    //   System.out.println("Food name: " + Name);
    //   System.out.println("Price: " + price);
    //   System.out.println();
    // }
}
