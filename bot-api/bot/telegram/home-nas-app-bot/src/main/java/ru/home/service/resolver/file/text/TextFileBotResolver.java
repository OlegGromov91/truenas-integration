package ru.home.service.resolver.file.text;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;
import ru.home.service.resolver.file.FileBotResolver;

import java.util.Objects;

import static ru.home.service.enums.FileSuffix.TXT;

@Component
public class TextFileBotResolver extends FileBotResolver {

    @Override
    protected boolean identifyFileType(Document document) {
        return Objects.equals(document.getMimeType(), TXT.getMimeType()) || document.getFileName().endsWith(TXT.getSuffix());
    }

    @Override
    protected boolean identifyCallBackData(String callbackData) {
        return false;
    }

}
