package ru.home.service.resolver.file;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.service.BotCommonService;

import java.util.Objects;

import static ru.home.service.enums.FileSuffix.TXT;

@Component
public class TextFileBotResolver extends FileBotResolver {

    protected TextFileBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    @Override
    public boolean canResolveMessage(Update update) {
        return super.canResolveMessage(update) && identifyFileType(update);
    }

    @Override
    protected boolean identifyFileType(Update update) {
        if (update.getMessage().hasDocument()) {
            Document document = update.getMessage().getDocument();
            return Objects.equals(document.getMimeType(), TXT.getMimeType()) || document.getFileName().endsWith(TXT.getSuffix());
        }
        return false;
    }

    @Override
    protected EditMessageText processCallbackQuery(CallbackQuery callbackQuery) {
        throw new RuntimeException("Not supported!!!");
    }

    @Override
    protected SendMessage processMessage(Message telegramMessage) {
    throw new RuntimeException("Not supported!!!");
    }

    @Override
    public boolean canResolveCallBack(Update update) {
        return false;
    }

}
