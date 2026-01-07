package com.java.mapper;

import com.java.dto.ProductRequestDto;
import com.java.dto.ProductResponseDto;
import com.java.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDto productRequestDto);
    ProductResponseDto toResponseDto(Product product);
}
