package Server;

import RestaurantDatabase.Restaurant;
import RestaurantDatabase.Food;
import RestaurantDatabase.RestaurantManager;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class FileOperation {

    private RestaurantManager restaurantManager=new RestaurantManager();
    private HashMap<String, String> restMap=new HashMap<>();
    private HashMap<String,String> userMap=new HashMap<>();

    public void readFoodFromFile()
    {
        String foodFilePath = "D://JavaFx-Restaurent//Restaurant//src//Server//menu.txt";
        try (Scanner scanner = new Scanner(Paths.get(foodFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] foodItem = line.split(",");
                int restaurantId = Integer.parseInt(foodItem[0]);
                String restName = foodItem[1];
                String category = foodItem[2];
                String name = foodItem[3];
                double price = Double.parseDouble(foodItem[4]);
                Food f = new Food(restaurantId,restName, category, name, price);
                restaurantManager.addFoodToList(f);
            }
        }
        catch(Exception e)
        {
            System.out.println("An error occured to read the file: " + e.getMessage());
        }
    }

    public void  readRestaurantFromFile()
    {
        String restaurantFilePath = "D://JavaFx-Restaurent//Restaurant//src//Server//restaurant.txt";
        try (Scanner scanner = new Scanner(Paths.get(restaurantFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ArrayList<String> categories = new ArrayList<>();
                String[] array = line.split(",");
                String password=array[0];
                int id = Integer.parseInt(array[1]);
                String name = array[2];
                double score = Double.parseDouble(array[3]);
                String price = array[4];
                int ZipCode = Integer.parseInt(array[5]);
                //String[] categories = new String[3];
                // for(int i=0;i<3;i++)
                // {
                //     categories[i]=array[i+6];
                // }
                for(int i=6;i<array.length;i++)
                {
                    categories.add(array[i]);
                }
                restMap.put(name,password);
                Restaurant r = new Restaurant(password,id, name, score, price, ZipCode, categories);
                restaurantManager.addRestaurant(r);
            }
        } catch (IOException e) 
        {
            System.out.println("An error occured to read the file " + e.getMessage());
        }
    }

    public void readUser()
    {
       String customerFilePath="D://JavaFx-Restaurent//Restaurant//src//Server//customer.txt";
       try(Scanner scanner = new Scanner(Paths.get(customerFilePath)))
       {
        while(scanner.hasNextLine())
        {
            String line =scanner.nextLine();
            String[] array =line.split(",",-1);
            String name=array[0];
            String password=array[1];
            userMap.put(name, password);
        }
       }
       catch(Exception e)
       {
        System.out.println("An error occured to open the file: " + e.getMessage());
       }
    }

    public void appendRestaurant(Restaurant r)
    {
        String filePath="D://JavaFx-Restaurent//Restaurant//src//Server//restaurant.txt";
        try
        {
            FileWriter fileWriter = new FileWriter(filePath,true);
            fileWriter.write(r.getId() + "," + r.getPassword() + "," + r.getName() + "," + r.getScore() + "," + r.getPrice() + "," + r.getZipCode());

            for(String str: r.getCategories())
            {
                fileWriter.write("," + str);  
            }


            fileWriter.write("\n");

            fileWriter.close();

            System.out.println("File have been appended");
        }
        catch(Exception e)
        {
            System.out.println("An error occured in appending " + e.getMessage());
        }
    }

    public void init()
    {
        readFoodFromFile();
        readRestaurantFromFile();
        readUser();
    }

    public RestaurantManager getRestaurantManager()
    {
        return restaurantManager;
    }

    public HashMap<String,String> getHashMap()
    {
        return restMap;
    }

    public HashMap<String,String> getUserMap()
    {
        return userMap;
    }
    
}
