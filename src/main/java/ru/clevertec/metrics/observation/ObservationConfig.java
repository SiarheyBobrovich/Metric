package ru.clevertec.metrics.observation;

import io.micrometer.observation.ObservationPredicate;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ObservationConfig {

    private final ObservationPredicate observationPredicate;
    private final CustomObservationHandler customObservationHandler;

    /**
     * Аспект чтобы работала аннотация @Observed
     * Авоматически подхватывает хэндлеры и предикаты
     */
    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry, ObservationProperties properties) {
        return new ObservedAspect(observationRegistry);
    }
}
