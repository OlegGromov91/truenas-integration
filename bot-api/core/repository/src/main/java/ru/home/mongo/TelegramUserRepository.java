package ru.home.mongo;

import ru.home.base.ApplicationMongoRepository;
import ru.home.model.user.TelegramUser;

import java.util.Optional;

public interface TelegramUserRepository extends ApplicationMongoRepository<TelegramUser> {

    Optional<TelegramUser> findByTgId(Long tgId);

    @Override
    default Class<TelegramUser> getType() {
        return TelegramUser.class;
    }

}
