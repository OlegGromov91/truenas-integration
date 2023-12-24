package ru.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.base.BotResolver;
import ru.home.exceptions.BotResolveException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class BotResolverService {

    @Autowired
    @Lazy
    private List<BotResolver> resolvers = new ArrayList<>();

    public BotApiMethod<? extends Serializable> resolve(Update update) {
        BotResolver botResolver = extractResolver(update);
        return botResolver.resolve(update);
    }

    private BotResolver extractResolver(Update update) {
        return resolvers.stream()
                .filter(resolver -> resolver.identifyResolver(update))
                .findFirst()
                .orElseThrow(() -> new BotResolveException("Can not find resolver"));
    }


}
