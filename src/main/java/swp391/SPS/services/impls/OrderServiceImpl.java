package swp391.SPS.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import swp391.SPS.entities.Accessory;
import swp391.SPS.entities.Order;
import swp391.SPS.entities.Phone;
import swp391.SPS.repositories.OrderRepository;
import swp391.SPS.services.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> ListOrderByUserId(int id) {
        return orderRepository.getOrderByUserId(id);
    }

    @Override
    public List<Phone> getProductByOrderId(int id) {
        return orderRepository.findPhonesByOrderId(id);
    }

//    @Override
//    public List<Accessory> getAccessoryByOrderId(int id) {
//        return orderRepository.findAccessoryByOrderId(id);
//    }

//    @Override
//    public List<Order> listOrderPhoneById(int id) {
//        return orderRepository.getOrderPhoneById(id);
//    }

}
