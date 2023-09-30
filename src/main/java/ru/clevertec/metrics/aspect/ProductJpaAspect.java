package ru.clevertec.metrics.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class ProductJpaAspect {

    private final MeterRegistry meterRegistry;
    private final Map<Class<?>, Map<String, Counter>> counter = new HashMap<>();


    @Pointcut("execution(* ru.clevertec.metrics.repository.*.*(..))")
    public void countPointCut() {
    }

    @Before("countPointCut()")
    public void beforeCountPointCut(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getSignature().getDeclaringType();
        String method = joinPoint.getSignature().getName();

        if (!counter.containsKey(clazz)) {
            counter.put(clazz, new HashMap<>());
        }

        Map<String, Counter> counterMap = counter.get(clazz);
        if (!counterMap.containsKey(method)) {
            counterMap.put(method, Counter.builder("db." + method).register(meterRegistry));
        }
        counterMap.get(method).increment();
    }
}
