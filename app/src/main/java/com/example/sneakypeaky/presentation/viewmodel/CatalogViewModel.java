package com.sneakypeaky.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.usecase.GetProductsUseCase;

import java.util.List;

public class CatalogViewModel extends ViewModel {
    private final GetProductsUseCase getProductsUseCase;
    private final MutableLiveData<List<Product>> products = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public CatalogViewModel(GetProductsUseCase getProductsUseCase) {
        this.getProductsUseCase = getProductsUseCase;
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadProducts() {
        loading.setValue(true);
        getProductsUseCase.execute(new ResultCallback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> data) {
                loading.postValue(false);
                error.postValue(null);
                products.postValue(data);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                error.postValue(message);
            }
        });
    }
}
