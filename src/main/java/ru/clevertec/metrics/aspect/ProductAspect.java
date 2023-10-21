package ru.clevertec.metrics.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.clevertec.metrics.data.ProductDto;
import ru.clevertec.metrics.service.impl.ProductMetricService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aspect
//@Component
@RequiredArgsConstructor
public class ProductAspect {
    private final Map<UUID, Counter> uuidCounterMap = new HashMap<>();

    private final ProductMetricService productMetricService;
    private final MeterRegistry meterRegistry;

    @Pointcut(value = "execution(public * ru.clevertec.metrics.service.ProductService.*(java.util.UUID))")
    public void getProductPointCut() {
    }

    @Pointcut(value = "execution(public * ru.clevertec.metrics.service.ProductService.*(..))")
    public void getProductDtoPointCut() {
    }

    @Before(value = "getProductPointCut() && args(uuid)")
    public void countGet(UUID uuid) {
        if (uuidCounterMap.containsKey(uuid)) {
            uuidCounterMap.get(uuid).increment();
        }
    }

    @AfterReturning(value = "getProductDtoPointCut()", argNames = "productDto", returning = "productDto")
    public void afterReturningProductDto(ProductDto productDto) {
        if (uuidCounterMap.containsKey(productDto.uuid())) {
            return;
        }

        Counter counter = Counter.builder("product." + productDto.name())
                .register(meterRegistry);
        uuidCounterMap.put(productDto.uuid(), counter);
    }

    @PostConstruct
    void initCounter() {
        Gauge.builder("product.gauge", productMetricService::getCountOfAllProducts)
                .register(meterRegistry);
    }
}
