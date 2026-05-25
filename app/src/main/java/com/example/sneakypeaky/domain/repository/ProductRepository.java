package com.sneakypeaky.domain.repository;

import com.sneakypeaky.domain.common.ResultCallback;
import com.sneakypeaky.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    void getProducts(ResultCallback<List<Product>> callback);

    void getProduct(int productId, ResultCallback<Product> callback);
}
