package ru.home.base;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.service.BotCommonService;

import java.io.Serializable;

public abstract class AbstractBotResolver extends AbstractMarkupAbleBotResolver {

    protected final BotCommonService commonService;

    protected AbstractBotResolver(BotCommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public BotApiMethod<? extends Serializable> resolve(Update update) {
        UpdateType type = UpdateType.getType(update);
        return switch (type) {
            case MESSAGE -> processMessage(update.getMessage());
            case CALLBACK_QUERY -> processCallbackQuery(update.getCallbackQuery());
        };

    }
    @Override
    public boolean identifyResolver(Update update) {
        UpdateType type = UpdateType.getType(update);
        return switch (type) {
            case MESSAGE -> canResolveMessage(update);
            case CALLBACK_QUERY -> canResolveCallBack(update);
        };
    }

    protected abstract boolean canResolveMessage(Update update);

    protected abstract boolean canResolveCallBack(Update update);

    protected abstract EditMessageText processCallbackQuery(CallbackQuery callbackQuery);

    protected abstract SendMessage processMessage(Message telegramMessage);


}
