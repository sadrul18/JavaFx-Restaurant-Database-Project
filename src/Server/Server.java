package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import RestaurantDatabase.RestaurantManager;
import Util.NetworkUtil;

public class Server {
    private HashMap<String, String> restMap=new HashMap<>();
    private HashMap<String, NetworkUtil> restUtilMap = new HashMap<>();
    private HashMap<String, NetworkUtil> userUtilMap = new HashMap<>();
    private HashMap<String,String> userMap=new HashMap<>();
    private FileOperation fileOperation=new FileOperation();
    private RestaurantManager restaurantManager=new RestaurantManager();

    ServerSocket serverSocket;

    private void  setHashMap()
    {
        restMap=fileOperation.getHashMap();
    }

    private void setRestManager()
    {
        restaurantManager=fileOperation.getRestaurantManager();
    }

    private void setUserMap()
    {
        userMap=fileOperation.getUserMap();
    }

    Server()
    {
         fileOperation.init();
         restMap=fileOperation.getHashMap();
         restaurantManager=fileOperation.getRestaurantManager();
         userMap=fileOperation.getUserMap();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException 
    {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap,restMap,userUtilMap,restUtilMap, networkUtil,restaurantManager);
    }

    public static void main(String[] args) {
        new Server();
    }

    
}
