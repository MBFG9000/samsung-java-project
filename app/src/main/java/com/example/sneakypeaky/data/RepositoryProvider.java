package com.sneakypeaky.data;

import com.sneakypeaky.data.api.RetrofitClient;
import com.sneakypeaky.data.repository.CartRepositoryImpl;
import com.sneakypeaky.data.repository.FirebaseAuthRepository;
import com.sneakypeaky.data.repository.ProductRepositoryImpl;
import com.sneakypeaky.domain.repository.AuthRepository;
import com.sneakypeaky.domain.repository.CartRepository;
import com.sneakypeaky.domain.repository.ProductRepository;
import com.google.firebase.auth.FirebaseAuth;

public class RepositoryProvider {
    private static ProductRepository productRepository;
    private static CartRepository cartRepository;
    private static AuthRepository authRepository;

    public static ProductRepository getProductRepository() {
        if (productRepository == null) {
            productRepository = new ProductRepositoryImpl(RetrofitClient.getApi());
        }
        return productRepository;
    }

    public static CartRepository getCartRepository() {
        if (cartRepository == null) {
            cartRepository = new CartRepositoryImpl();
        }
        return cartRepository;
    }

    public static AuthRepository getAuthRepository() {
        if (authRepository == null) {
            authRepository = new FirebaseAuthRepository(FirebaseAuth.getInstance());
        }
        return authRepository;
    }
}
