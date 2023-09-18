package RestaurantDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class RestaurantManager implements Serializable
{
     List<Restaurant> restaurantList =new ArrayList<Restaurant>();
     List<Food> foodList =new ArrayList<Food>();
     List<String> allCategories =new ArrayList<>();

    public List<Restaurant> getRestaurantList()
    {
       return restaurantList;
    }

    public List<Food> getFoodList()
    {
      return foodList;
    }
    public boolean addRestaurant(Restaurant r)
    {
        for (Restaurant restaurent : restaurantList) 
        {
            if(restaurent.getId()==r.getId() || restaurent.getName().equalsIgnoreCase(r.getName()))
            {
                System.out.println("Restaurent with this name or Id exists");
                return false;
            }
        }
        //String[] str=r.getCategories();
        // for (String string : allCategories) 
        // {
        //     for (String string2 : r.getCategories()) 
        //     {
        //         if(!string.equalsIgnoreCase(string2) )
        //         {
        //             //continue;
        //             allCategories.add(string2);
        //         }
        //     }
        // }
        for (var cat:r.getCategories()){
            if ( cat.length()>0 && !allCategories.contains(cat)){
                allCategories.add(cat);
            }
        }
        restaurantList.add(r);
        return true;
    }

    public void showCat()
    {
        for (String string : allCategories) 
        {
            System.out.println("" + string);
        }
        System.out.println(allCategories.size());
    }

    public void categoryWiseRestaurant()
    {
      for (String string : allCategories) 
      {
         System.out.print(string + ": ");
         for (Restaurant restaurant:restaurantList) 
         {
            ArrayList<String> str=restaurant.getCategories();
            for (int i=0;i<str.size();i++) 
            {
                if(string.equalsIgnoreCase(str.get(i)))
                {
                    System.out.print(restaurant.getName() + ", ");
                }
            }
         }
        System.out.println();
      }
    }

    public void addFoodToList(Food f)
    {
        foodList.add(f);
        int restId=f.getRestaurentId();
        for(Restaurant restaurant:restaurantList)
        {
            if(restId==restaurant.getId())
            {
                restaurant.addFood(f);
            }
        }
    }

    public void addFood(Food f, String restaurantName)
    {
        int restaurantId=-1;
       // boolean flag=false;
       for (Restaurant restaurant: restaurantList) 
       {
           if(restaurant.getName().equalsIgnoreCase(restaurantName))
           {
            restaurantId=restaurant.getId();
           }
       }

       for (Food food : foodList) 
       {
         if(food.getRestaurentId()==restaurantId && !food.getName().equalsIgnoreCase(f.getName()) && !food.getCategory().equalsIgnoreCase(f.getCategory() ))
         {
            foodList.add(f);
            System.out.println("Food have been added successfully to the menu");
            return;
         }
       }
        
       System.out.println("No such restaurant with your input reataurant name.");
       return;
    }


    public ArrayList<Restaurant> searchRestaurantByName(String restaurantName)
    {
        ArrayList<Restaurant> r=new ArrayList<>();
        boolean flag=false;
        String name=restaurantName.toLowerCase();
        for (Restaurant restaurant:restaurantList) 
        {
            String restName=restaurant.getName().toLowerCase();
            if(restName.contains(name))
            {
                r.add(restaurant);
                flag=true;
            }
        }
        if(flag)
        {
            return r;
        }
        return null;
    }

   public ArrayList<Restaurant> searchRestaurantByPrice(String price)
    {
        ArrayList<Restaurant> R=new ArrayList<>();
        boolean flag=false;
        for (Restaurant restaurant: restaurantList) 
        {
            if(restaurant.getPrice().equals(price))
            {
                R.add(restaurant);
                flag=true;
            }
        }
       if(flag)
       {
        return R;
       }
       else
       {
         return null;
       }
    }

    public ArrayList<Restaurant> searchRestaurantByScore(double score1,double score2)
    {
      ArrayList<Restaurant> R=new ArrayList<>();
      boolean flag=false;
      for (Restaurant restaurant : restaurantList) 
      {
        if(restaurant.getScore()>=score1 && restaurant.getScore()<=score2)
        {
            R.add(restaurant);
            flag=true;
        }
      }
      if(flag)
      {
        return R;
      }
      else
      {
        return null;
      }
    }

    public ArrayList<Restaurant> searchRestaurantByCategory(String categoryName)
    {
        ArrayList<Restaurant> R=new ArrayList<>();
        boolean flag=false;
        String category=categoryName.toLowerCase();
        for (Restaurant restaurant :restaurantList) 
        {
            for (String string:restaurant.getCategoryList()) 
            {
                String str;
                if(string!=null)
                {
                    str=string.toLowerCase();
                }
                else
                {
                    str=string;
                }
               if( str!=null && str.contains(category))
               {
                 flag=true;
                 R.add(restaurant);
                 break;
               } 
            }
        }

        if(flag)
        {
            return R;
        }
        else 
        {
            return null;
        }
    }

    public ArrayList<Restaurant> searchRestaurantByZipCode(int ZipCode)
    {
        ArrayList<Restaurant> R= new ArrayList<>();
        for (Restaurant restaurant: restaurantList) 
        {
             if(restaurant.getZipCode()==ZipCode)
             {
                R.add(restaurant);
                return R;
             }
        }
        return null;
    }

    public ArrayList<Food> searchFoodByName(String name)
    {
      ArrayList<Food> f=new ArrayList<>();
      boolean flag=false;
      String foodName=name.toLowerCase();
      for (Food food : foodList) 
      {
        String Name=food.getName().toLowerCase();
         if(Name.contains(foodName))
         {
            f.add(food);
            flag=true;
         }
      }
      if(flag)
      {
        return f;
      }
      return null;
    }

   public ArrayList<Food> searchFoodInReastaurant(String restaurantName, String foodName)
   {
    boolean flag=false;
    int restaurantId=-1;
    ArrayList<Food> f=new ArrayList<>();
    String name=foodName.toLowerCase();
    for (Restaurant restaurant: restaurantList) 
    {
        if(restaurant.getName().equalsIgnoreCase(restaurantName))
        {
            restaurantId=restaurant.getId();
        }
    } 
    for (Food food : foodList) 
    {
        String Name=food.getName().toLowerCase();
        if(food.getRestaurentId()==restaurantId && Name.contains(name))
        {
          f.add(food);
          flag=true;
        }
    }
    if(flag)
    {
        return f;
    }
    return null;
   }

   public ArrayList<Food> searchFoodByCategory(String category)
   {
     ArrayList<Food> f=new ArrayList<>();
     boolean flag=false;
     String categoryName=category.toLowerCase();
     for (Food food : foodList) 
     {
        String foodCategory=food.getCategory().toLowerCase();
        if(foodCategory.contains(categoryName))
        {
            f.add(food);
            flag=true;
        }
     }
     if(flag)
     {
        return f;
     }
     return null;
   }

   public ArrayList<Food> searchFoodByCategoryInRestaurant(String restaurantName, String category)
   {
    ArrayList<Food> f=new ArrayList<>();
    boolean flag=false;
    int restaurantId=-1;
    String categoryName=category.toLowerCase();
    for (Restaurant restaurant:restaurantList) 
    {
        if(restaurant.getName().equalsIgnoreCase(restaurantName))
        {
            restaurantId=restaurant.getId();
        }
    }
    for (Food food : foodList) 
    {
        String foodCategory=food.getName().toLowerCase();
        if(food.getRestaurentId()==restaurantId && foodCategory.contains(categoryName))
        {
            flag=true;
            f.add(food);
        }
    }
    if(flag)
    {
        return f;
    }
    return null;
   }

   public ArrayList<Food> searchByPriceRange(double lowerPrice, double higherPrice)
   {
    ArrayList<Food> f=new ArrayList<>();
    boolean flag=false;
    for (Food food : foodList) 
    {
        if(food.getPrice()>=lowerPrice && food.getPrice()<=higherPrice)
        {
            flag=true;
            f.add(food);
        }
    }
    if(flag)
    {
        return f;
    }
    return null;
   }

   public ArrayList<Food> searchFoodByPriceRangeInRestaurant(double lowerPrice, double higherPrice, String restaurantName)
   {
     ArrayList<Food> f=new ArrayList<>();
     boolean flag=false;
     int restaurantId=-1;
    for (Restaurant restaurant:restaurantList) 
    {
        if(restaurant.getName().equalsIgnoreCase(restaurantName))
        {
            restaurantId=restaurant.getId();
        }
    }

    for (Food food : foodList) 
    {
        if(food.getRestaurentId()==restaurantId && food.getPrice()>=lowerPrice && food.getPrice()<=higherPrice)
        {
            flag=true;
            f.add(food);
        }
    }
    if(flag)
    {
        return f;
    }
     return null;
   }
  
   public ArrayList<Food> costliestFoodItem(String restaurantName)
   {
    ArrayList<Food> f=new ArrayList<>();
    boolean flag=false;
    int restaurantId=-1;
    for (Restaurant restaurant:restaurantList) 
    {
        if(restaurant.getName().equalsIgnoreCase(restaurantName))
        {
            restaurantId=restaurant.getId();
        }
    }
    for (Food food : foodList) 
    {
        if(food.getRestaurentId()==restaurantId)
        {
            f.add(food);
            flag=true;
        }
    }

    double maxPrice=0;
    int[] maxIndex=new int[f.size()];
    int n=0;
    for (Food food:f) 
    {
        if(food.getPrice()>maxPrice)
        {
            maxPrice=food.getPrice();
        }
    }
    for(int i=0;i<f.size();i++)
    {
        Food food=f.get(i);
        if(food.getPrice()==maxPrice)
        {
          maxIndex[n++]=i;
        }
    }
    ArrayList<Food> F=new ArrayList<>();
    for(int i=0;i<n;i++)
    {
        F.add(f.get(maxIndex[i]));
    }
    if(flag)
    {
        return F;
    }
    return null;
   }

   public void restaurantWithTotalFoodItem()
   {
    System.out.println();
    for (Restaurant restaurant:restaurantList) 
    {
        int sumOfFoodItem=0;
        for (Food food : foodList) 
        {
            if(restaurant.getId()==food.getRestaurentId())
            {
                sumOfFoodItem++;
            }
        }
        System.out.println("" + restaurant.getName() + ": " + sumOfFoodItem);
    }
    System.out.println();
   }

}
