package ru.home.service.resolver.menu.test;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.MessageHandler;
import ru.home.service.BotCommonService;
import ru.home.service.enums.MenuCommands;
import ru.home.service.resolver.menu.MenuBotResolver;

import java.util.Objects;

@Component
public class TestMenuBotResolver extends MenuBotResolver implements MessageHandler {

    private static final String COMMAND = MenuCommands.TEST.getCommand();

    protected TestMenuBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    @Override
    protected boolean identifyCommand(Update update) {
        return Objects.equals(update.getMessage().getEntities().get(0).getText(), COMMAND);
    }

    @Override
    protected EditMessageText processCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }

    @Override
    protected SendMessage processMessage(Message telegramMessage) {
        return getPreFilledMessage(telegramMessage).text("Test").build();
    }

    @Override
    public boolean canResolveCallBack(Update update) {
        return false;
    }
}
