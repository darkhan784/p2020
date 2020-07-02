package org.aidarkhanov.commands;

import org.aidarkhanov.data.DataProvider;
import org.aidarkhanov.entities.WordEntry;
import org.aidarkhanov.services.TopWordsService;
import org.aidarkhanov.services.impl.TopWordsServiceImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component(
        service = NewsTopWordsCommand.class,
        immediate = true
//        property = {
//                CommandProcessor.COMMAND_SCOPE + ":String=news",
//                CommandProcessor.COMMAND_FUNCTION + ":String=stats"
//        }
)
public class NewsTopWordsCommand {
    private static final int TOP_WORD_COUNT = 10;
    private DataProvider dataProvider;
    private final Map<String, DataProvider> providers = new HashMap<String, DataProvider>();

    @Reference(
            service = DataProvider.class,
            policy = ReferencePolicy.DYNAMIC,
            bind = "binder",
            unbind = "unbinder"
    )
    protected void binder(DataProvider dataProvider) {
        System.out.println("Bound: " + dataProvider.getName());
        providers.put(dataProvider.getName(), dataProvider);
    }

    public void unbinder(DataProvider dataProvider) {
        System.out.println("Unbound: " + dataProvider.getName());
        providers.remove(dataProvider.getName());
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

