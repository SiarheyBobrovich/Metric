package ru.clevertec.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class MetricsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsApplication.class, args);
    }

}
