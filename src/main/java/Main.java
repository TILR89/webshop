import entity.Delivery;
import entity.Orders;
import entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DeliveryService;
import service.OrdersService;
import service.UsersService;
import utils.ConnectionPool;
import utils.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main {
    private static final Logger logger = LogManager.getLogger(UsersService.class);

    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        //util.getConnection();


        OrdersService ordersService = new OrdersService();
        Orders orders = new Orders();
        Users user = new Users();
        UsersService users = new UsersService();
        DeliveryService ds = new DeliveryService();

        //Get all
      List<Users> usersList = users.getAll();;
      for (Users u : usersList)
        {
           // System.out.println(u);
            logger.info(u);
        }
      //logger.info(usersList);
/*


        List<Orders> ordersList = ordersService.getAll();
        for(Orders o : ordersList){
        System.out.println(ordersList);};



        //add user
        /*
        user.setUser_name("John Miles");
      user.setUser_email("miles@hotmail.com");
      user.setUser_password("1234");
        users.add(user);

         */

/*
        orders = ordersService.getById(2);
        System.out.println(orders);


        List<Delivery> deliveryList = ds.getAll();
        for (Delivery d : deliveryList) {
            System.out.println(d);
        }

 */



    }
}
