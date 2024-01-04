package ru.home.service.resolver.file.torrent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.home.service.TelegramCommonService;
import ru.home.service.enums.FileSuffix;
import ru.home.service.resolver.file.FileBotResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TorrentFileBotResolver extends FileBotResolver {

    private final TelegramCommonService commonService;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    @Value("#{'${file.category}'.split(',')}")
    private Set<String> fileCategories;
    private final Map<String, String> categoriesWithCallBackData = new HashMap<>();
    private static final String PREFIX_BUTTON_NAME = "TORRENT_";


    @PostConstruct
    public void init() {
        fileCategories.forEach(category -> categoriesWithCallBackData.put(category, PREFIX_BUTTON_NAME + category));
        this.inlineKeyboardMarkup = buildKeyboardKeyboardMarkup(categoriesWithCallBackData);
    }

    @Override
    protected boolean identifyFileType(Document document) {
        return Objects.equals(document.getMimeType(), FileSuffix.TORRENT.getMimeType()) || document.getFileName().endsWith(FileSuffix.TORRENT.getSuffix());
    }

    @Override
    protected boolean identifyCallBackData(String callbackData) {
        if (callbackData.startsWith(PREFIX_BUTTON_NAME)) {
            String buttonData = callbackData.substring(PREFIX_BUTTON_NAME.length());
            return categoriesWithCallBackData.containsKey(buttonData);
        }
        return false;
    }

    @Override
    protected SendMessage resolveMessage(Message message) {
        Document document = extractDocumentFromMessage(message);
        String fileId = document.getFileId();
        String fileName = document.getFileName();
        Long tgUserId = extractUserIdFromMessage(message);

        commonService.getFileInfo(fileId, fileName, tgUserId);
        return getPreFilledMessage(message)
                .text("Выберите тип загружаемого файла")
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    protected EditMessageText resolveCallbackQuery(CallbackQuery callbackQuery) {
        Long tgUserId = extractUserIdFromCallbackQuery(callbackQuery);
        String category = callbackQuery.getData().substring(PREFIX_BUTTON_NAME.length());
        commonService.handleFileToTorrentClient(tgUserId, category);
        return getPreFilledCallbackMessage(callbackQuery)
                .text("Торрент файл будет добавлен в загрузки")
                .build();
    }

}
