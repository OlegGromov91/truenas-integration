package ru.home.service.resolver.file;

import com.google.common.base.Strings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.base.AbstractBotResolver;

public abstract class FileBotResolver extends AbstractBotResolver {

    @Override
    public boolean canResolveMessage(Message message) {
        if (message.hasDocument()) {
            Document document = message.getDocument();
            return identifyFileType(document);
        }
        return false;
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
    public boolean canResolveCallBack(CallbackQuery callbackQuery) {
        return !Strings.isNullOrEmpty(callbackQuery.getData()) && identifyCallBackData(callbackQuery.getData());
    }

    protected abstract boolean identifyFileType(Document document);

    protected abstract boolean identifyCallBackData(String callbackData);


}
