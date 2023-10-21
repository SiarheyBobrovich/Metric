package ru.clevertec.metrics.observation;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationPredicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@EnableConfigurationProperties(ObservationProperties.class)
public class CustomObservationPredicate implements ObservationPredicate {

    private final List<String> names = new ArrayList<>();

    public CustomObservationPredicate(ObservationProperties properties) {
        properties.getEnable().entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .forEach(names::add);
    }

    @Override
    public boolean test(String s, Observation.Context context) {
        return names.stream().anyMatch(s::startsWith);
    }
}
