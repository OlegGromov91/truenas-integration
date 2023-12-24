package ru.home.service.resolver.url;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.AbstractBotResolver;
import ru.home.service.BotCommonService;

public abstract class AbstractUrlResolver extends AbstractBotResolver {

    protected final static String TYPE = "url";

    protected AbstractUrlResolver(BotCommonService commonService) {
        super(commonService);
    }


    @Override
    public boolean canResolveMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasEntities() &&
                update.getMessage().getEntities().stream().anyMatch(messageEntity -> messageEntity.getType().equals(TYPE));
    }
}
