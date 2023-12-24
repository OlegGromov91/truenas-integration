package ru.home.service.resolver.file;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.AbstractBotResolver;
import ru.home.service.BotCommonService;

public abstract class FileBotResolver extends AbstractBotResolver {


    protected FileBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    @Override
    public boolean canResolveMessage(Message message) {
        return identifyFileType(message);
    }

    @Override
    protected EditMessageText resolveCallbackQuery(CallbackQuery callbackQuery) {
        throw new UnsupportedOperationException("CallbackQuery not supported");
    }

    @Override
    protected SendMessage resolveMessage(Message message) {
        throw new UnsupportedOperationException("Message not supported");
    }

    @Override
    public boolean canResolveCallBack(Update update) {
        return false;
    }

    protected abstract boolean identifyFileType(Message message);


}
