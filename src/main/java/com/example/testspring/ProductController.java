package com.example.testspring;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PutMapping("/create")
    public Product createProduct(@RequestBody ProductCreate productCreate) {
        Product product = new Product();
        product.setName(productCreate.getName());
        product.setDescription(productCreate.getDescription());
        product.setPrice(productCreate.getPrice());

        repository.save(product);

        return product;
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        Iterable<Product> iterator = repository.findAll();

        List<Product> products = new ArrayList<>();
        for (Product product : iterator)
            products.add(product);

        return products;
    }


    @Getter
    @Setter
    public static final class ProductCreate {
        private String name;
        private String description;
        private int price;
    }

}
