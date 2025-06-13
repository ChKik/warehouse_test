package com.warehouse_test.service;

import com.warehouse_test.entity.Product;
import com.warehouse_test.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //constructor
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //crud
    @Override
    public List<Product> getProducts(){
       return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {

        if (!productRepository.findByName(product.getName()).isEmpty()) {
            throw new IllegalArgumentException("A product with that name already exists.");
        }

        if (product.getWeight() == null || product.getWeight().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0.");
        }
        //ta created timestamps at the ginoyn aytomata
        product.setName(product.getName().trim());
        product.setWeight(product.getWeight());
        product.setPrice(product.getPrice());
        product.setCategory(product.getCategory());


        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));

        if (product.getWeight() != null && product.getWeight().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0.");
        }

        existing.setName(product.getName().trim());
        existing.setWeight(product.getWeight());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());


        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }

         productRepository.deleteById(id);
    }

    //business
    @Override
    public List<Product> getProductsByName(String name) {
        if(!productRepository.findByName(name).isEmpty()) {
            throw new IllegalArgumentException("A product with that name does not exist.");
        }
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByWeight(BigDecimal weight){
        if(!productRepository.findByWeight(weight).isEmpty()) {
            throw new IllegalArgumentException("A product with that weight does not exist.");
        }
        return productRepository.findByWeight(weight);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        if(!productRepository.findByCategory(category).isEmpty()) {
            return productRepository.findByCategory(category);
        }
        else
            throw new EntityNotFoundException("Product category does not exist.");
    }
}
