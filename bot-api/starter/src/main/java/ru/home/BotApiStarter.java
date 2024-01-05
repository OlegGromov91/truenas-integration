package ru.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.home.bot.HomeNasAppTgBot;
import ru.home.criteria.mongo.TelegramUserCriteria;
import ru.home.model.user.TelegramUser;
import ru.home.repository.RepositoryFactory;
import ru.home.repository.mongo.TelegramUserRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "ru.home")
@EntityScan(basePackages = {"ru.home"})
@EnableMongoRepositories(basePackages = {"ru.home"})
public class BotApiStarter implements CommandLineRunner {

    @Autowired
    private HomeNasAppTgBot telegramBot;
    @Autowired
    private RepositoryFactory repos;
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BotApiStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String image = "ssssssssssssdasad";


        TelegramUserRepository rs = repos.getRepository(TelegramUser.class);
        Optional<TelegramUser> optionalTelegramUser = rs.findByTgId(544908050L);

        if (optionalTelegramUser.isEmpty()) {
            TelegramUser user = TelegramUser.builder()
                    .firstName("lolol")
                    .tgId(544908050L)
                    .name("sdsd")
                    .userName("yellow")
                    .email("gg@mail.ru")
                    .build();
            user = repos.save(user);
            System.out.println(user);
        } else {
            System.out.println(optionalTelegramUser);
        }


        List<TelegramUser> telegramUser = repos.criteria().search(TelegramUserCriteria.builder()
                .tgId(optionalTelegramUser.get().getTgId())
                .build());


        Query query = new Query();
        query.addCriteria(Criteria.where("tgId").is("544908050"));
        List<TelegramUser> rg = mongoTemplate.find(query, TelegramUser.class);

        System.out.println(telegramUser);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("tgId").is("544908050"));
//        mongoTemplate.find(query, TelegramUser.class);


//        SmallFile testFile = SmallFile.builder()
//                .fileName("test_name_test")
//                .filePath("file_path_test")
//                .type(FileType.TORRENT)
//                .file(SmallFile.toBinary(image.getBytes()))
//                .userId(user.getId())
//                .build();
//
//        testFile = repos.save(testFile);
//
//       SmallFileRepository rr = repos.getRepository(testFile.getClass());
//
//        Optional<SmallFile> tr = rr.findById(testFile.getId());
//        List<SmallFile> tr2 = rr.findByUserId(user.getId());

        //  System.out.println(tr);


    }
}
