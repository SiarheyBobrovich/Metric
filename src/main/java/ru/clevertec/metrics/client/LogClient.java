package ru.clevertec.metrics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "log", url = "${app.log.url}")
public interface LogClient {

    @PostMapping
    void sendLog(@RequestParam String message);
}
