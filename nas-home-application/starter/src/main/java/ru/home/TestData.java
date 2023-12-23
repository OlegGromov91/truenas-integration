package ru.home;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.model.user.ApplicationUser;
import ru.home.model.user.TelegramUser;
import ru.home.repository.TorrentRepository;
import ru.home.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class TestData {

//    @Autowired
//    private ApplicationTypeConverter converter;
    @Autowired
    private TorrentRepository torrentRepository;
    @Autowired
    UserRepository userRepository;


    public void preFilledAllData(){
        preFilledTestUsers();

    }

    public void preFilledTestUsers() {


        List<ApplicationUser> users = List.of(
                ApplicationUser.builder()
                        .firstName("TestUser")
                        .telegramUser(buildTgUser(544908050L, "Oleg"))
                        .build(),
                ApplicationUser.builder()
                        .firstName("TestUser2")
                        .telegramUser(buildTgUser(544908051L, "Oleg2"))
                        .build()
        );

        userRepository.saveAll(users);

        Optional<ApplicationUser> user = userRepository.findByTelegramUserId(544908050L);
        log.debug("user with id 544908050L isPresent? = " + user.isPresent());
    }


    private TelegramUser buildTgUser(Long id, String name) {
        return TelegramUser.builder()
                .id(id)
                .firstName(name)
                .isBot(false)
                .build();
    }
}
