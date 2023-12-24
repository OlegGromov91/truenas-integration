package ru.home.service.resolver.menu;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.base.AbstractBotResolver;
import ru.home.service.BotCommonService;
import ru.home.service.enums.MenuCommands;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;


public abstract class MenuBotResolver extends AbstractBotResolver {


    protected MenuBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    @Override
    public boolean canResolveMessage(Update update) {
        boolean isUpdateHasOneEntity = update.getMessage().hasEntities() && update.getMessage().getEntities().size() == 1;
        if (isUpdateHasOneEntity) {
            MessageEntity entity = update.getMessage().getEntities().get(0);
            return entity.getType().equals(MenuCommands.TYPE) && identifyCommand(update);
        }
        return false;
    }

    protected abstract boolean identifyCommand(Update update);

}
