package swp391.SPS.services;

//import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Order;
import swp391.SPS.entities.Phone;

import java.util.List;

public interface    OrderService {
    List<Order> getAllOrder();
    List<Order> ListOrderByUserId(int id);
//    List<Phone> getProductByOrderId(int id);
}
