package com.sneakypeaky.presentation.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sneakypeaky.domain.repository.CartRepository;
import com.sneakypeaky.presentation.viewmodel.CartViewModel;

public class CartViewModelFactory implements ViewModelProvider.Factory {
    private final CartRepository cartRepository;

    public CartViewModelFactory(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CartViewModel.class)) {
            return (T) new CartViewModel(cartRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
