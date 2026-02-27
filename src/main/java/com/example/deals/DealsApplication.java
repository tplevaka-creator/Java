package com.example.deals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.deals.service.DealService;
import com.example.deals.util.CsvDealLoader;

import java.io.InputStreamReader;

@SpringBootApplication
public class DealsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealsApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DealService service, CsvDealLoader loader) {
        return args -> {
            // On startup we can load sample CSV from resources if available
            try (var in = DealsApplication.class.getResourceAsStream("/sample-deals.csv")) {
                if (in != null) {
                    var deals = loader.load(new InputStreamReader(in));
                    deals.forEach(service::addDeal);
                }
            }
        };
    }
}
