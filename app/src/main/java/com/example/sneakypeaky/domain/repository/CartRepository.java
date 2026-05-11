package com.sneakypeaky.domain.repository;

import androidx.lifecycle.LiveData;

import com.sneakypeaky.domain.model.CartItem;
import com.sneakypeaky.domain.model.Product;

import java.util.List;

public interface CartRepository {
    LiveData<List<CartItem>> getCartItems();

    void addToCart(Product product);

    void removeFromCart(int productId);

    void clearCart();

    double getTotalPrice();
}
