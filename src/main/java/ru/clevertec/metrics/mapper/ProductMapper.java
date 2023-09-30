package ru.clevertec.metrics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.clevertec.metrics.data.ProductDto;
import ru.clevertec.metrics.entity.Product;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);
}
