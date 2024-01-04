package ru.home.base;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface MessageHandler {

    default Document extractDocumentFromMessage(Message telegramMessage) {
        return Optional.ofNullable(telegramMessage.getDocument())
                .orElseThrow(() -> new RuntimeException("Не найден Document в telegramMessage"));
    }

    default Long extractChatIdFromMessage(Message telegramMessage) {
        return Optional.ofNullable(telegramMessage.getChatId())
                .orElseThrow(() -> new RuntimeException("Не найден chatId в telegramMessage"));
    }

    default Long extractUserIdFromMessage(Message telegramMessage) {
        return Optional.ofNullable(telegramMessage.getFrom())
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("Не найден UserId в telegramMessage"));
    }

    default SendMessage.SendMessageBuilder getPreFilledMessage(Message telegramMessage) {
        return SendMessage.builder()
                .chatId(extractChatIdFromMessage(telegramMessage));
    }
}
