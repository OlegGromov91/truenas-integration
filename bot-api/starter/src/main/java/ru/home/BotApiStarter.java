package ru.home;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ru.home")
@EntityScan(basePackages = {"ru.home"})
public class BotApiStarter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BotApiStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
