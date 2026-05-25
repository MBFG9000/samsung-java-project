package com.sneakypeaky.data.mapper;

import com.sneakypeaky.data.dto.ProductDto;
import com.sneakypeaky.domain.model.Product;

public class ProductMapper {
    public static Product toDomain(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        double rate = dto.rating != null ? dto.rating.rate : 0;
        int count = dto.rating != null ? dto.rating.count : 0;
        return new Product(
            dto.id,
            dto.title,
            dto.price,
            dto.description,
            dto.category,
            dto.image,
            rate,
            count
        );
    }
}
