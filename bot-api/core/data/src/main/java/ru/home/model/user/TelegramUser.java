package ru.home.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "telegram_user")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser extends ApplicationUser {

    @NotNull
    @Indexed(unique = true)
    private Long tgId;
    private String firstName;
    private String secondName;
    private String userName;
    private Boolean isBot;
}
