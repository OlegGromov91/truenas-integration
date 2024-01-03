package ru.home.base;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {

    default Document extractDocumentFromMessage(Message telegramMessage) {
        return telegramMessage.getDocument();
    }

    default Long extractChatIdFromMessage(Message telegramMessage) {
        return telegramMessage.getChatId();
    }

    default Long extractUserIdFromMessage(Message telegramMessage) {
        return telegramMessage.getFrom().getId();
    }

    default SendMessage.SendMessageBuilder getPreFilledMessage(Message telegramMessage) {
        return SendMessage.builder()
                .chatId(extractChatIdFromMessage(telegramMessage));
    }
}
