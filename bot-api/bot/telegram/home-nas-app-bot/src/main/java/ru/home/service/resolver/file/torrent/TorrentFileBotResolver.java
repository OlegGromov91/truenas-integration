package ru.home.service.resolver.file.torrent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.home.configuration.AvailableTorrentCategories;
import ru.home.service.TelegramCommonService;
import ru.home.service.enums.FileSuffix;
import ru.home.service.resolver.file.FileBotResolver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TorrentFileBotResolver extends FileBotResolver {

    private final TelegramCommonService commonService;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private final AvailableTorrentCategories torrentCategories;
    private final Map<String, AvailableTorrentCategories.Categories> categories = new HashMap<>();
    private static final String PREFIX_BUTTON_NAME = "TORRENT_";


    @PostConstruct
    public void init() {
        torrentCategories.getChannelConfigurations().forEach(category -> categories.put(PREFIX_BUTTON_NAME + category.getCategory(), category));
        Map<String, String> categoriesWithCallBackData = new HashMap<>();
        categories.keySet().forEach(entry -> categoriesWithCallBackData.put(entry, categories.get(entry).getCategory()));
        this.inlineKeyboardMarkup = buildKeyboardKeyboardMarkup(categoriesWithCallBackData);
    }

    @Override
    protected boolean identifyFileType(Document document) {
        return Objects.equals(document.getMimeType(), FileSuffix.TORRENT.getMimeType()) || document.getFileName().endsWith(FileSuffix.TORRENT.getSuffix());
    }

    @Override
    protected boolean identifyCallBackData(String callbackData) {
        return Objects.nonNull(categories.get(callbackData));
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
        AvailableTorrentCategories.Categories category = categories.get(callbackQuery.getData());
        commonService.handleFileToTorrentClient(tgUserId, category.getCategory(), category.getCategoryFolder());
        return getPreFilledCallbackMessage(callbackQuery)
                .text("Торрент файл будет добавлен в загрузки")
                .build();
    }

}
