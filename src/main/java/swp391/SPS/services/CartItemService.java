package swp391.SPS.services;

import swp391.SPS.entities.CartItem;

import java.util.Collection;

public interface CartItemService {
    void add(CartItem item);

    void remove(int id);

    CartItem update(int productId, int quality);

    void clear();

    Collection<CartItem> getAllItems();

    int getCount();

    double getAmmount();
}
