package com.warehouse_test.controller;


import com.warehouse_test.entity.Product;
import com.warehouse_test.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins="*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product = productService.getProducts();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        return productService.getProductById(id)
                .map(product-> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct=productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
        try{
            Product updatedProduct=productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
        List<Product> products = productService.getProductByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName){
        Product product = productService.getProductsByName(productName).get(0);
        return ResponseEntity.ok(product);
    }

    // einai kai to weight alla mallon den exei nohma san method telika.
}
