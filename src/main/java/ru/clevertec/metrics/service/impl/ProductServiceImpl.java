package ru.clevertec.metrics.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.metrics.data.ProductDto;
import ru.clevertec.metrics.mapper.ProductMapper;
import ru.clevertec.metrics.repository.ProductRepository;
import ru.clevertec.metrics.service.ProductService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService, ProductMetricService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new EntityNotFoundException("Product " + id + " not found"));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return Optional.of(productDto)
                .map(productMapper::toProduct)
                .map(productRepository::save)
                .map(productMapper::toProductDto)
                .orElseThrow();
    }

    @Override
    public Long getCountOfAllProducts() {
        return productRepository.getCountOfAllProducts();
    }
}
