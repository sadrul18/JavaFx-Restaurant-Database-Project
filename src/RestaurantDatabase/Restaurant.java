package RestaurantDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Restaurant implements Serializable
{
    private String password;
    private int Id;
    private String Name;
    private double Score;
    private String price;
    private int ZipCode;
    ArrayList<String> Categories = new ArrayList<>();
    private List<Food> foodList=new ArrayList<>();

    ArrayList<String> categoryList=new ArrayList<>();
    public Restaurant()
    {
        
    }
   public Restaurant(String password,int Id, String Name, double Score,String price, int ZipCode, ArrayList<String> Categories)
    {
       this.password=password;
       this.Id=Id;
       this.Name=Name;
       this.Score=Score;
       this.price=price;
       this.ZipCode=ZipCode;
       this.Categories=Categories;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }
    
    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setScore(double score) {
        Score = score;
    }

    public void setPrice(String price)
    {
        this.price=price;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }

    public void setCategories(ArrayList<String> categories) {
        Categories = categories;
    }
    
    public int getId() {
        return Id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName() {
        return Name;
    }

    public double getScore() {
        return Score;
    }

    public String getPrice()
    {
        return price;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public ArrayList<String> getCategories() {
        return Categories;
    }

    public ArrayList<String> getCategoryList()
    {
        for (String string : Categories) 
        {
            categoryList.add(string);
        }
        return categoryList;
    }

    public void addFood(Food f)
    {
        foodList.add(f);
    }

    public List<Food> getFoodList()
    {
        return foodList;
    }

    public void displayAllDetails()
    {
        System.out.println();
        System.out.println("Restaurant id: " + Id);
        System.out.println("Name: " + Name);
        System.out.println("Score: " + Score);
        System.out.println("Zip-code: "+ ZipCode);
        System.out.println("Categories: ");
        System.out.println("Size of foodlist: " + foodList.size());
        // for(String str:Categories)
        // {
        //     if(str!=null)
        //     {
        //         System.out.println(str);
        //     }
        // }
        for (Food f: foodList) 
        {
            System.out.println(f.getName());
        }
        System.out.println();
    }
}
