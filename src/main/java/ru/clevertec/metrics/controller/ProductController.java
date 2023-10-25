package ru.clevertec.metrics.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{uuid}")
    //Включает наблюдение за методом
    @Observed(name = "products.get.uuid",
            lowCardinalityKeyValues = {"product.key", "uuid"},
            contextualName = "contextualName")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID uuid) {
        ProductDto productDto = productService.getById(uuid);
        log.info("After Get");

        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    @Observed(name = "products.save.new")
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody ProductDto productDto) {
        log.info("Create new product: {}", productDto);
        ProductDto createdDto = productService.create(productDto);

        return ResponseEntity.ok(createdDto);
    }
}
