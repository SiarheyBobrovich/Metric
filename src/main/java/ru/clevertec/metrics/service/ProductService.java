package ru.clevertec.metrics.service;

import ru.clevertec.metrics.data.ProductDto;

import java.util.UUID;

public interface ProductService {
    ProductDto getById(UUID uuid);

    ProductDto create(ProductDto productDto);
}
