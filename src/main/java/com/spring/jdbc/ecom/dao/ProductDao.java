package com.spring.jdbc.ecom.dao;

import com.spring.jdbc.ecom.model.Product;
import com.spring.jdbc.ecom.model.ProductWithCategory;

import java.util.List;

public interface ProductDao {

    Product create(Product product);

    Product update(Product product, int productId);

    void delete(int productId);

    //get all products
    List<Product> getAll();

    //get single product
    Product get(int productId);

    //search Product
    List<Product> search(String keyword);

    //get all products of category
    List<Product> getAllByCategory(int CatId);

    //get all products with category

    List<ProductWithCategory> getAllWithCategory();

}
