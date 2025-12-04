package com.pluralsight.NorthwindTradersSpringBootPt2.dao.interfaces;

import com.pluralsight.NorthwindTradersSpringBootPt2.models.Product;

import java.util.List;

public interface IProductDAO {
    Product add(Product product);

    List<Product> getAllProducts();

    Product getProductById(int ProductId);
    
    void update(int ProductId, Product Product);

    void delete(int ProductId);
}

