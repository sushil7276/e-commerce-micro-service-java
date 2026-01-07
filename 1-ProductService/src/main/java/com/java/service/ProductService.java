package com.java.service;

import com.java.dto.ProductRequestDto;
import com.java.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto);

    ProductResponseDto getProductById(Long id);

    boolean isProductExit(Long id);

    boolean deleteProductById(Long id);

}
