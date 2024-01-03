package ru.home;

import ru.home.base.ApplicationMongoRepository;
import ru.home.model.user.TelegramUser;

public interface TelegramUserRepository extends ApplicationMongoRepository<TelegramUser> {
    @Override
    default Class<TelegramUser> getType() {
        return TelegramUser.class;
    }

}
