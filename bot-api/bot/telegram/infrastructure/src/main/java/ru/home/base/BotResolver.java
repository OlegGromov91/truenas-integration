package ru.home.base;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public interface BotResolver extends CallbackQueryHandler, MessageHandler {

    BotApiMethod<? extends Serializable> resolve(Update update);

    /**
     * Метод должен однозначно определить подходит ли резолвер для данных, приходящих с телеграмма
     *
     * @param update
     * @return
     */

    boolean identifyResolver(Update update);

}
