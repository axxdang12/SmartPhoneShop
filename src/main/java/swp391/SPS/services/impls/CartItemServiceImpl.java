package swp391.SPS.services.impls;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import swp391.SPS.entities.CartItem;
import swp391.SPS.services.CartItemService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class CartItemServiceImpl implements CartItemService {
    Map<Integer, CartItem> maps=new HashMap<>();
    @Override
    public void add(CartItem item){
        CartItem cartItem=maps.get(item.getProductId());
        if(cartItem==null){
            maps.put(item.getProductId(), item);
        }else{
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }
    }
    @Override
    public void remove(int id){
        maps.remove(id);
    }

    @Override
    public CartItem update(int productId, int quality){
        CartItem cartItem=maps.get(productId);
        cartItem.setQuantity(quality);
        return cartItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItems(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public double getAmmount(){
        return maps.values().stream().mapToDouble(item-> item.getQuantity()* item.getPrice()).sum();
    }
}
