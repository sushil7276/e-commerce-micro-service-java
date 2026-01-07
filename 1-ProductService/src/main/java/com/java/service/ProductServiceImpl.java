package com.java.service;

import com.java.dto.ProductRequestDto;
import com.java.dto.ProductResponseDto;
import com.java.exception.ProductNotFoundException;
import com.java.mapper.ProductMapper;
import com.java.models.Product;
import com.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productMapper
                .toResponseDto(productRepository.save
                        (productMapper.toEntity(productRequestDto)));
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        product.setStock(productRequestDto.getStock());
        productRepository.save(product);

        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
       return productRepository.findById(id).map(productMapper::toResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public boolean isProductExit(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean deleteProductById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
