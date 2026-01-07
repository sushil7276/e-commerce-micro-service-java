package com.java.controller;

import com.java.dto.ProductRequestDto;
import com.java.dto.ProductResponseDto;
import com.java.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        log.info("ProductController :: createProduct {}", productRequestDto.getName());
        ProductResponseDto product = productService.createProduct(productRequestDto);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productId,
                                                            @Valid @RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto productResponseDto = productService.updateProduct(productId, productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/exits/{productId}")
    public ResponseEntity<Boolean> isProductExits(@PathVariable Long productId) {

        return new ResponseEntity<>(productService.isProductExit(productId), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId) {
        log.info("ProductController :: getProductById {}", productId);
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public boolean deleteProductById(@PathVariable Long productId) {

        return productService.deleteProductById(productId);
    }
}
