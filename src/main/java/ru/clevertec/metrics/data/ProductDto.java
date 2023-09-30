package ru.clevertec.metrics.data;

import java.util.UUID;

public record ProductDto(UUID uuid,
                         String name,
                         Integer count
) {
}
