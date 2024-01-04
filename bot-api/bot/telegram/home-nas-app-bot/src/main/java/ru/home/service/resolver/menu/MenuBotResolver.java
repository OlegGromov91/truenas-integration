package ru.home.service.resolver.menu;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.AbstractBotResolver;
import ru.home.service.enums.MenuCommands;


public abstract class MenuBotResolver extends AbstractBotResolver {

    @Override
    public boolean canResolveMessage(Message message) {
        boolean isEntitySizeOne = message.hasEntities() && message.getEntities().size() == 1;
        if (isEntitySizeOne) {
            MessageEntity entity = message.getEntities().stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("При попытке извлечь сущность произошла обшибка"));
            return entity.getType().equals(MenuCommands.TYPE) && identifyCommand(message);
        }
        return false;
    }

    @Override
    protected EditMessageText resolveCallbackQuery(CallbackQuery callbackQuery) {
        throw new UnsupportedOperationException("CallbackQuery not supported");
    }

    @Override
    public boolean canResolveCallBack(CallbackQuery callbackQuery) {
        return false;
    }

    protected abstract boolean identifyCommand(Message message);

}
