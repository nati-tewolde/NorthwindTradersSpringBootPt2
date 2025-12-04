package com.pluralsight.NorthwindTradersSpringBootPt2.services;

import com.pluralsight.NorthwindTradersSpringBootPt2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPt2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private IProductDAO ProductDAO;

    @Autowired
    public ProductService(IProductDAO ProductDAO) {
        this.ProductDAO = ProductDAO;
    }

    public Product addProduct(Product Product) {
        return ProductDAO.add(Product);
    }

    public List<Product> getAllProducts() {
        return ProductDAO.getAllProducts();
    }

    public Product getProductById(int ProductId) {
        return ProductDAO.getProductById(ProductId);
    }

    public void updateProduct(int ProductId, Product Product) {
        ProductDAO.update(ProductId, Product);
    }

    public void deleteProduct(int ProductId) {
        ProductDAO.delete(ProductId);
    }
}