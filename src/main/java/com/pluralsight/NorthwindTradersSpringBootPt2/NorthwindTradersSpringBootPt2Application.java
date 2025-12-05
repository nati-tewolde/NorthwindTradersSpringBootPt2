package com.pluralsight.NorthwindTradersSpringBootPt2;

import com.pluralsight.NorthwindTradersSpringBootPt2.dao.impl.SimpleProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPt2.models.Product;
import com.pluralsight.NorthwindTradersSpringBootPt2.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NorthwindTradersSpringBootPt2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootPt2Application.class, args);

        ProductService ProductService = context.getBean(ProductService.class);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========== Ledger Application ==========");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listProducts(ProductService);
                    break;
                case 2:
                    addProduct(scanner, ProductService);
                    break;
                case 3:
                    updateProduct(scanner, ProductService);
                    break;
                case 4:
                    deleteProduct(scanner, ProductService);
                    break;
                case 5:
                    searchProduct(scanner, ProductService);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void listProducts(ProductService ProductService) {
        System.out.println("========== List of Products ==========");
        List<Product> Products = ProductService.getAllProducts();
        for (Product Product : Products) {
            System.out.println(Product);
        }
        System.out.println();
    }

    private static void addProduct(Scanner scanner, ProductService ProductService) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the Category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Product Product = new Product(name, categoryId, price);
        Product newProduct = ProductService.addProduct(Product);

        System.out.println("Product added successfully.\n");
        System.out.println(newProduct);
        System.out.println();
    }

    private static void updateProduct(Scanner scanner, ProductService ProductService) {
        System.out.print("Enter the Product ID to update: ");
        int ProductId = scanner.nextInt();
        scanner.nextLine();

        Product existingProduct = ProductService.getProductById(ProductId);
        if (existingProduct == null) {
            System.out.println("Product not found.\n");
            return;
        }

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Product updatedProduct = new Product(ProductId, name, categoryId, price);
        ProductService.updateProduct(ProductId, updatedProduct);

        System.out.println("Product updated successfully.\n");
    }

    private static void deleteProduct(Scanner scanner, ProductService ProductService) {
        System.out.print("Enter the Product ID to delete: ");
        int ProductId = scanner.nextInt();
        scanner.nextLine();

        Product existingProduct = ProductService.getProductById(ProductId);
        if (existingProduct == null) {
            System.out.println("Product not found.\n");
            return;
        }

        ProductService.deleteProduct(ProductId);

        System.out.println("Product deleted successfully.\n");
    }

    private static void searchProduct(Scanner scanner, ProductService ProductService) {
        System.out.print("Enter the Product ID to search: ");
        int ProductId = scanner.nextInt();
        scanner.nextLine();

        Product Product = ProductService.getProductById(ProductId);
        if (Product == null) {
            System.out.println("Product not found.\n");
        } else {
            System.out.println("========== Product Details ==========");
            System.out.println(Product);
            System.out.println();
        }
    }

}