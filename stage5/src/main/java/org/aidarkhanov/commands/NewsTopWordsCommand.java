package org.aidarkhanov.commands;

import org.aidarkhanov.data.DataProvider;
import org.aidarkhanov.data.impl.AifDataProvider;
import org.aidarkhanov.data.impl.LentaDataProvider;
import org.aidarkhanov.data.impl.YandexDataProvider;
import org.aidarkhanov.entities.WordEntry;
import org.aidarkhanov.services.TopWordsService;
import org.aidarkhanov.services.impl.TopWordsServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsTopWordsCommand {
    private static final int TOP_WORD_COUNT = 10;

    private final Map<String, DataProvider> providers = new HashMap<>();

    public NewsTopWordsCommand() {
        providers.put("lenta", new LentaDataProvider());
        providers.put("aif", new AifDataProvider());
        providers.put("yandex", new YandexDataProvider());
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

