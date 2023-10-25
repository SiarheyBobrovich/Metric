package ru.clevertec.metrics.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import ru.clevertec.metrics.data.ProductDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMessageProducer {

    private static final String logBinding = "logProducer-out-0";

    private final StreamBridge streamBridge;

    public void sendLog(ProductDto productDto) {
        streamBridge.send(logBinding, productDto);
    }
}
