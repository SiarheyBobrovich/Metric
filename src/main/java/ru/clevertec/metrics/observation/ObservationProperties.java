package ru.clevertec.metrics.observation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("management.metrics")
@Data
@AllArgsConstructor
public class ObservationProperties {

    private final Map<String, Boolean> enable = new HashMap<>();
    private final boolean all = true;
}
