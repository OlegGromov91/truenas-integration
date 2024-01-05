package ru.home.criteria.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.home.criteria.base.AbstractMongoCriteria;
import ru.home.model.user.TelegramUser;

@Getter
@Setter
@SuperBuilder
@ToString
public class TelegramUserCriteria extends AbstractMongoCriteria<TelegramUser> {

    private String id;
    private String name;
    private String email;
    private Long tgId;
    private String firstName;
    private String secondName;
    private String userName;
    private Boolean isBot;

    @Override
    public Class<TelegramUser> getType() {
        return TelegramUser.class;
    }
}
