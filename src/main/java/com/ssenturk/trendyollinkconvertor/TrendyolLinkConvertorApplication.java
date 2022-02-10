package com.ssenturk.trendyollinkconvertor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.ssenturk.trendyollinkconvertor.*"})
public class TrendyolLinkConvertorApplication
        implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TrendyolLinkConvertorApplication.class, args);
    }

    @Override
    public void run(String... args) {

    }
}