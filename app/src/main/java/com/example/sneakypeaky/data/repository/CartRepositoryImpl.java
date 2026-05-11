package com.sneakypeaky.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sneakypeaky.domain.model.CartItem;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.repository.CartRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartRepositoryImpl implements CartRepository {
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());

    @Override
    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    @Override
    public void addToCart(Product product) {
        List<CartItem> current = cartItems.getValue();
        List<CartItem> items = current != null ? new ArrayList<>(current) : new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.increment();
                cartItems.setValue(items);
                return;
            }
        }
        items.add(new CartItem(product, 1));
        cartItems.setValue(items);
    }

    @Override
    public void removeFromCart(int productId) {
        List<CartItem> current = cartItems.getValue();
        List<CartItem> items = current != null ? new ArrayList<>(current) : new ArrayList<>();
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                iterator.remove();
                break;
            }
        }
        cartItems.setValue(items);
    }

    @Override
    public void clearCart() {
        cartItems.setValue(new ArrayList<>());
    }

    @Override
    public double getTotalPrice() {
        List<CartItem> items = cartItems.getValue();
        double total = 0;
        if (items != null) {
            for (CartItem item : items) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        return total;
    }
}
