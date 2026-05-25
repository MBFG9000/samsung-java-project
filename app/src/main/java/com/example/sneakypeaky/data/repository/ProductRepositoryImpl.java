package com.sneakypeaky.data.repository;

import com.sneakypeaky.data.api.FakeStoreApi;
import com.sneakypeaky.data.mapper.ProductMapper;
import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {
    private final FakeStoreApi api;

    public ProductRepositoryImpl(FakeStoreApi api) {
        this.api = api;
    }

    @Override
    public void getProducts(ResultCallback<List<Product>> callback) {
        api.getProducts().enqueue(new Callback<List<com.sneakypeaky.data.dto.ProductDto>>() {
            @Override
            public void onResponse(Call<List<com.sneakypeaky.data.dto.ProductDto>> call, Response<List<com.sneakypeaky.data.dto.ProductDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = new ArrayList<>();
                    for (com.sneakypeaky.data.dto.ProductDto dto : response.body()) {
                        Product mapped = ProductMapper.toDomain(dto);
                        if (mapped != null) {
                            products.add(mapped);
                        }
                    }
                    callback.onSuccess(products);
                } else {
                    callback.onError("Failed to load products");
                }
            }

            @Override
            public void onFailure(Call<List<com.sneakypeaky.data.dto.ProductDto>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getProduct(int productId, ResultCallback<Product> callback) {
        api.getProduct(productId).enqueue(new Callback<com.sneakypeaky.data.dto.ProductDto>() {
            @Override
            public void onResponse(Call<com.sneakypeaky.data.dto.ProductDto> call, Response<com.sneakypeaky.data.dto.ProductDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = ProductMapper.toDomain(response.body());
                    if (product != null) {
                        callback.onSuccess(product);
                    } else {
                        callback.onError("Failed to map product");
                    }
                } else {
                    callback.onError("Failed to load product");
                }
            }

            @Override
            public void onFailure(Call<com.sneakypeaky.data.dto.ProductDto> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
}
