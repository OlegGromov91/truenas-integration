package ru.home.service.resolver.menu.test;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import ru.home.base.MessageHandler;
import ru.home.service.enums.MenuCommands;
import ru.home.service.resolver.menu.MenuBotResolver;

import java.util.Objects;

@Component
public class TestMenuBotResolver extends MenuBotResolver implements MessageHandler {

    private static final String COMMAND = MenuCommands.TEST.getCommand();

    @Override
    protected boolean identifyCommand(Message message) {
        String entityText = message.getEntities().stream()
                .findFirst()
                .map(MessageEntity::getText)
                .orElse(MenuCommands.UNDEFINED);
        return Objects.equals(entityText, COMMAND);
    }

    @Override
    protected SendMessage resolveMessage(Message telegramMessage) {
        return getPreFilledMessage(telegramMessage).text("Test").build();
    }


}
