package com.sneakypeaky.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sneakypeaky.domain.model.CartItem;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.repository.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    private final CartRepository cartRepository;

    public CartViewModel(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartRepository.getCartItems();
    }

    public void addToCart(Product product) {
        cartRepository.addToCart(product);
    }

    public void removeFromCart(int productId) {
        cartRepository.removeFromCart(productId);
    }

    public void clearCart() {
        cartRepository.clearCart();
    }

    public double getTotalPrice() {
        return cartRepository.getTotalPrice();
    }
}
