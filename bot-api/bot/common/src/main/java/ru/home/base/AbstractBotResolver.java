package ru.home.base;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

public abstract class AbstractBotResolver extends AbstractMarkupAbleBotResolver {

    @Override
    public BotApiMethod<? extends Serializable> resolve(Update update) {
        UpdateType type = UpdateType.getType(update);
        return switch (type) {
            case MESSAGE -> resolveMessage(update.getMessage());
            case CALLBACK_QUERY -> resolveCallbackQuery(update.getCallbackQuery());
        };

    }

    @Override
    public boolean identifyResolver(Update update) {
        UpdateType type = UpdateType.getType(update);
        return switch (type) {
            case MESSAGE -> canResolveMessage(update.getMessage());
            case CALLBACK_QUERY -> canResolveCallBack(update);
        };
    }

    protected abstract boolean canResolveMessage(Message message);

    protected abstract boolean canResolveCallBack(Update update);

    protected abstract EditMessageText resolveCallbackQuery(CallbackQuery callbackQuery);

    protected abstract SendMessage resolveMessage(Message telegramMessage);


}
