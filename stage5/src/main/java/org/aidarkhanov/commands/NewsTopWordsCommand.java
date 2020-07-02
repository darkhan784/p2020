package org.aidarkhanov.commands;

import org.aidarkhanov.services.impl.TopWordsServiceImpl;
import org.aidarkhanov.data.DataProvider;
import org.osgi.service.component.annotations.*;
import org.aidarkhanov.entities.WordEntry;
import org.aidarkhanov.services.TopWordsService;

import java.util.*;


@Component(
        service = NewsTopWordsCommand.class,
        immediate = true
)
public class NewsTopWordsCommand {
    private static final int TOP_WORD_COUNT = 10;
    private final Map<String, DataProvider> providers = new HashMap<>();
    private DataProvider dataProvider;

    @Reference(
            service = DataProvider.class,
            policy = ReferencePolicy.DYNAMIC,
            bind = "binder",
            unbind = "unbinder"
    )
    protected void binder(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        System.out.println(dataProvider.getName());
        providers.put(dataProvider.getName(), dataProvider);
    }

    public void unbinder(DataProvider mediaPortal) {
        System.out.println("Unbind " + mediaPortal.getName());
        providers.remove(mediaPortal.getName());
    }

    public void stats() {
        System.out.println("Choose source: ");
        System.out.println(String.join(", ", providers.keySet()));
    }

    public void stats(String source) {
        if (providers.containsKey(source)) {
            DataProvider provider = providers.get(source);
            TopWordsService topWordsService = new TopWordsServiceImpl(provider);
            List<WordEntry> topWords = topWordsService.getTopWords(TOP_WORD_COUNT);

            for (WordEntry entry : topWords) {
                System.out.println(entry.getWord() + ": " + entry.getOccurrenceCount());
            }
        } else {
            System.out.println("No such provider");
        }
    }
}

