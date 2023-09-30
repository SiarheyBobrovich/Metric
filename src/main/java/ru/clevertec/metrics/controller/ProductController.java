package ru.clevertec.metrics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.metrics.data.ProductDto;
import ru.clevertec.metrics.service.ProductService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID uuid) {
        ProductDto productDto = productService.getById(uuid);

        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto) {
        ProductDto createdDto = productService.create(productDto);

        return ResponseEntity.ok(createdDto);
    }
}
