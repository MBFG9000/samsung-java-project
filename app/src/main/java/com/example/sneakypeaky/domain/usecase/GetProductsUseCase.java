package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.repository.ProductRepository;

import java.util.List;

public class GetProductsUseCase {
    private final ProductRepository repository;

    public GetProductsUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void execute(ResultCallback<List<Product>> callback) {
        repository.getProducts(callback);
    }
}
