package ru.home.service.resolver.file;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.AbstractBotResolver;
import ru.home.service.BotCommonService;

public abstract class FileBotResolver extends AbstractBotResolver {


    protected FileBotResolver(BotCommonService commonService) {
        super(commonService);
    }

    /**
     * Добавлять сюда другие типы файлов под типу message.hasDocument();
     *
     * @param update
     * @return
     */
    @Override
    public boolean canResolveMessage(Update update) {
        return (update.getMessage().hasDocument() || update.getMessage().hasAudio());
    }

    protected abstract boolean identifyFileType(Update update);


}
