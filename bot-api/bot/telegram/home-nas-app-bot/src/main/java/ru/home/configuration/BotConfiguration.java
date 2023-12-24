package ru.home.configuration;

import ru.home.service.enums.MenuCommands;
import ru.home.service.enums.TorrentMenuResolverButtonData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class BotConfiguration {

    @Bean(value = "torrentMenuInlineKeyboardMarkup")
    public InlineKeyboardMarkup torrentMenuInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(
                Arrays.stream(TorrentMenuResolverButtonData.values()).map(button -> List.of(button.getButton())).collect(Collectors.toList())
        );
        return inlineKeyboardMarkup;
    }

    @Bean(value = "menuCommands")
    public SetMyCommands setMyCommands() {
        List<BotCommand> botCommands = new ArrayList<>();
        Arrays.stream(MenuCommands.values()).map(MenuCommands::getBotCommand).forEach(botCommands::add);
        return new SetMyCommands(botCommands, new BotCommandScopeDefault(), null);
    }

    @Bean()
    @Scope(SCOPE_PROTOTYPE)
    public SendMessage sendMessage() {
        return new SendMessage();
    }

}
