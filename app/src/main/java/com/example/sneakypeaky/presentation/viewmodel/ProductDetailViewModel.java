package com.sneakypeaky.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.usecase.GetProductUseCase;

public class ProductDetailViewModel extends ViewModel {
    private final GetProductUseCase getProductUseCase;
    private final MutableLiveData<Product> product = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public ProductDetailViewModel(GetProductUseCase getProductUseCase) {
        this.getProductUseCase = getProductUseCase;
    }

    public LiveData<Product> getProduct() {
        return product;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadProduct(int productId) {
        loading.setValue(true);
        getProductUseCase.execute(productId, new ResultCallback<Product>() {
            @Override
            public void onSuccess(Product data) {
                loading.postValue(false);
                error.postValue(null);
                product.postValue(data);
            }

            @Override
            public void onError(String message) {
                loading.postValue(false);
                error.postValue(message);
            }
        });
    }
}
