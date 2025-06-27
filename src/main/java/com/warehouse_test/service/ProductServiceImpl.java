package com.warehouse_test.service;

import com.warehouse_test.dto.CreateProductDTO;
import com.warehouse_test.dto.ProductResponseDTΟ;
import com.warehouse_test.dto.UpdateProductDTO;
import com.warehouse_test.entity.Product;
import com.warehouse_test.mapper.ProductMapper;
import com.warehouse_test.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponseDTΟ> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<ProductResponseDTΟ> getProductById(long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO);
    }

    @Override
    public CreateProductDTO createProduct(CreateProductDTO dto) {
        if (!productRepository.findByName(dto.name()).isEmpty()) {
            throw new IllegalArgumentException("A product with that name already exists.");
        }

        if (dto.weight().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0.");
        }

        Product product = productMapper.toProductEntity(dto);
        productRepository.save(product);
        return dto;
    }

    @Override
    public UpdateProductDTO updateProduct(long id, UpdateProductDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));

        if (dto.weight() != null && dto.weight().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product weight must be greater than 0.");
        }

        productMapper.updateProductFromDto(dto, existing);
        productRepository.save(existing);
        return dto;
    }

    @Override
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDTΟ> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("A product with that name does not exist.");
        }
        return products.stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProductResponseDTΟ> getProductByWeight(BigDecimal weight) {
        List<Product> products = productRepository.findByWeight(weight);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("A product with that weight does not exist.");
        }
        return products.stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ProductResponseDTΟ> getProductByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("Product category does not exist.");
        }
        return products.stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }
}