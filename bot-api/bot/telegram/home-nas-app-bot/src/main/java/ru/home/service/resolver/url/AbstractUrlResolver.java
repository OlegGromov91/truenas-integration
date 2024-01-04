package ru.home.service.resolver.url;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.base.AbstractBotResolver;

public abstract class AbstractUrlResolver extends AbstractBotResolver {
    protected final static String TYPE = "url";
    @Override
    public boolean canResolveMessage(Message message) {
        return message.hasEntities() && message.getEntities().stream().anyMatch(messageEntity -> messageEntity.getType().equals(TYPE));
    }
}
