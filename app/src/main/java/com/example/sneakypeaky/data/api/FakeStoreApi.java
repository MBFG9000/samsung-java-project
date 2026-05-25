package com.sneakypeaky.data.api;

import com.sneakypeaky.data.dto.ProductDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeStoreApi {
    @GET("products")
    Call<List<ProductDto>> getProducts();

    @GET("products/{id}")
    Call<ProductDto> getProduct(@Path("id") int id);
}
