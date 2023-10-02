package ru.clevertec.metrics.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import ru.clevertec.metrics.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT SUM(count) FROM Product")
    @Cacheable(cacheNames = "count_products", key = "'count'")
    Long getCountOfAllProducts();

    @Modifying
    @NonNull
    @CacheEvict(value = "count_products", key = "'count'")
    <S extends Product> S save(@NonNull S product);
}
