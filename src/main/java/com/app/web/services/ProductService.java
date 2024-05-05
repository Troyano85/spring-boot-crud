package com.app.web.services;

import com.app.web.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product>listAllProducts();

    public Product saveProduct(Product product);

    public Product findById(Long id);

    public Product updateProduct(Product product);

    public  void deleteProduct(Long id);
}
