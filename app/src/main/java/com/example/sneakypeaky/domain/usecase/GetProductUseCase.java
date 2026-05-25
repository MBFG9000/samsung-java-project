package com.sneakypeaky.domain.usecase;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;
import com.sneakypeaky.domain.repository.ProductRepository;

public class GetProductUseCase {
    private final ProductRepository repository;

    public GetProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void execute(int productId, ResultCallback<Product> callback) {
        repository.getProduct(productId, callback);
    }
}
