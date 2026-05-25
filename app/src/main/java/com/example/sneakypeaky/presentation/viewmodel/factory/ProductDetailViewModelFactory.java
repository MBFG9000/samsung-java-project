package com.sneakypeaky.presentation.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sneakypeaky.domain.usecase.GetProductUseCase;
import com.sneakypeaky.presentation.viewmodel.ProductDetailViewModel;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {
    private final GetProductUseCase getProductUseCase;

    public ProductDetailViewModelFactory(GetProductUseCase getProductUseCase) {
        this.getProductUseCase = getProductUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductDetailViewModel.class)) {
            return (T) new ProductDetailViewModel(getProductUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
