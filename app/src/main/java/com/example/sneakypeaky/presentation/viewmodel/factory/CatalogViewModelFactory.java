package com.sneakypeaky.presentation.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sneakypeaky.domain.usecase.GetProductsUseCase;
import com.sneakypeaky.presentation.viewmodel.CatalogViewModel;

public class CatalogViewModelFactory implements ViewModelProvider.Factory {
    private final GetProductsUseCase getProductsUseCase;

    public CatalogViewModelFactory(GetProductsUseCase getProductsUseCase) {
        this.getProductsUseCase = getProductsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CatalogViewModel.class)) {
            return (T) new CatalogViewModel(getProductsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
