package ru.home.base;

import org.telegram.telegrambots.meta.api.objects.Update;

public enum UpdateType {

    MESSAGE,
    CALLBACK_QUERY,
    ;

    public static UpdateType getType(Update update) {
        if (update.hasMessage()) {
            return MESSAGE;
        }
        if (update.hasCallbackQuery()) {
            return CALLBACK_QUERY;
        }
        throw new IllegalArgumentException("Невозможно оперделить тип запроса");
    }
}
