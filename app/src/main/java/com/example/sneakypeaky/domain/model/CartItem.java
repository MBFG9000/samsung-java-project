package com.sneakypeaky.domain.model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increment() {
        quantity += 1;
    }

    public void decrement() {
        if (quantity > 1) {
            quantity -= 1;
        }
    }
}
