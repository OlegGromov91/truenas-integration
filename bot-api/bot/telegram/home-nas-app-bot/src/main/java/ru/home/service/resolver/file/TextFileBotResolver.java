package ru.home.service.resolver.file;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.service.BotCommonService;

import java.util.Objects;

import static ru.home.service.enums.FileSuffix.TXT;

@Component
public class TextFileBotResolver extends FileBotResolver {

    protected TextFileBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    @Override
    protected boolean identifyFileType(Message message) {
        if (message.hasDocument()) {
            Document document = message.getDocument();
            return Objects.equals(document.getMimeType(), TXT.getMimeType()) || document.getFileName().endsWith(TXT.getSuffix());
        }
        return false;
    }

    @Override
    protected SendMessage resolveMessage(Message message) {
        return super.resolveMessage(message);
    }


}
