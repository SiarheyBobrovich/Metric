package ru.clevertec.metrics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.metrics.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT SUM(count) FROM Product")
    Long getCountOfAllProducts();
}
