package org.stage5.osgi.commands;

import org.stage5.osgi.data.DataProvider;
import org.stage5.osgi.data.impl.AifDataProvider;
import org.stage5.osgi.data.impl.LentaDataProvider;
import org.stage5.osgi.data.impl.YandexDataProvider;
import org.stage5.osgi.entities.WordEntry;
import org.stage5.osgi.services.TopWordsService;
import org.stage5.osgi.services.impl.TopWordsServiceImpl;

import java.util.*;



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

