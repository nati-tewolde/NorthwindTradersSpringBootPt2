package com.pluralsight.NorthwindTradersSpringBootPt2.dao.impl;

import com.pluralsight.NorthwindTradersSpringBootPt2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPt2.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SimpleProductDAO implements IProductDAO {
    private List<Product> Products;

    public SimpleProductDAO() {
        this.Products = new ArrayList<>();
        Products.add(new Product("Banana", 1, 3.99));
        Products.add(new Product("Band Aids", 2, 10.99));
        Products.add(new Product("Water Bottle", 3, 29.99));
    }

    @Override
    public Product add(Product Product) {
        int maxId = 0;
        for (Product p : Products) {
            if (p.getProductId() > maxId) {
                maxId = p.getProductId();
            }
        }
        Product.setProductId(maxId + 1);
        Products.add(Product);
        return Product;
    }


    @Override
    public List<Product> getAllProducts() {
        return Products;
    }

    @Override
    public Product getProductById(int ProductId) {
        for (Product Product : Products) {
            if (Product.getProductId() == ProductId) {
                return Product;
            }
        }
        return null;
    }

    @Override
    public void update(int ProductId, Product Product) {
        int index = getProductIndex(ProductId);
        if (index != -1) {
            Products.set(index, Product);
        }
    }

    @Override
    public void delete(int ProductId) {
        int index = getProductIndex(ProductId);
        if (index != -1) {
            Products.remove(index);
        }
    }

    private int getProductIndex(int ProductId) {
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getProductId() == ProductId) {
                return i;
            }
        }
        return -1;
    }
}
