package com.example.inventoryTrackingWebApp.service;

import com.example.inventoryTrackingWebApp.mapper.ProductMapper;
import com.example.inventoryTrackingWebApp.model.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Product getProduct(Integer productId) {
        return productMapper.getProduct(productId);
    }

    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    public int insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    public int deleteProduct(Integer productId) {
        return productMapper.deleteProduct(productId);
    }

    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

}
