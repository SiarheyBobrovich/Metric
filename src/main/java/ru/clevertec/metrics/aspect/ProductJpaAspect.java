package ru.clevertec.metrics.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.clevertec.metrics.util.StringsUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
public class ProductJpaAspect {

    private final MeterRegistry meterRegistry;
    private final Map<Class<?>, Map<String, Timer>> timers = new HashMap<>();

    @SuppressWarnings("unused")
    @Pointcut("execution(* ru.clevertec.metrics.repository.*.*(..))")
    public void countPointCut() {
    }

    @Around("countPointCut()")
    public Object aroundJpa(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> clazz = pjp.getSignature().getDeclaringType();
        String method = pjp.getSignature().getName();

        putToTimers(clazz, method);

        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            recordTimer(clazz, method, System.currentTimeMillis() - start);
        }
    }

    private void putToTimers(Class<?> clazz, String method) {
        if (!timers.containsKey(clazz)) {
            timers.put(clazz, new HashMap<>());
        }

        Map<String, Timer> timerMap = timers.get(clazz);

        if (!timerMap.containsKey(method)) {
            Timer timer = Timer.builder("db." + StringsUtil.toLowerSnakeCase(method))
                    .register(meterRegistry);
            timerMap.put(method, timer);
        }
    }

    private void recordTimer(Class<?> clazz, String method, Long time) {
        timers.get(clazz)
                .get(method)
                .record(time, TimeUnit.MILLISECONDS);
    }
}
