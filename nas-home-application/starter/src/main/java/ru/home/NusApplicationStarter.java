package ru.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "ru.home")
@EnableJpaRepositories(basePackages = {"ru.home"})
@EntityScan(basePackages = {"ru.home"})
@EnableScheduling
public class NusApplicationStarter implements CommandLineRunner {

    @Autowired
    TestData preFill;
//
//    @Autowired
//    TelegramBot telegramBot;

    public static void main(String[] args) {
        SpringApplication.run(NusApplicationStarter.class, args);
    }

    @Override
    public void run(String... args) {
        preFill.preFilledTestUsers();
       // preFill.preFilledTestTorrents();
    }

}
