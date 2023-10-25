package ru.clevertec.metrics.observation;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomObservationHandler implements ObservationHandler<Observation.Context> {

    @Override
    //Вызывается первым
    public void onStart(Observation.Context context) {
        //Перед вызовом @Observed методов
        Object o = context.getLowCardinalityKeyValue("product.key");
        log.info("Старт наблюдения: {} with key: {}", context.getName(), o);
        context.put("время", System.currentTimeMillis());
    }

    @Override
    // Вызывается 4 если эксепшн то 5
    public void onStop(Observation.Context context) {
        //После вызова @Observed методов
        long time = context.getOrDefault("время", 0L);
        log.info("Стоп наблюдения: {}, time: {}", context.getName(), System.currentTimeMillis() - time);
    }

    @Override
    // Вызывается если эксепшн то 4
    public void onError(Observation.Context context) {
        // Наблюдение за @Observed методами при эксцепшенах
        log.info("Error: {}", Objects.requireNonNull(context.getError()).getMessage());
    }

    @Override
    public void onEvent(@NonNull Observation.Event event, @NonNull Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    // Вызывается вторым
    public void onScopeOpened(@NonNull Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    // Вызывается третьим
    public void onScopeClosed(@NonNull Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(@NonNull Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public boolean supportsContext(@NonNull Observation.Context context) {
        return true;
    }
}
