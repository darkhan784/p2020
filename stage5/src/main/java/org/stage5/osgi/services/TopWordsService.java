package org.stage5.osgi.services;

import org.stage5.osgi.entities.WordEntry;

import java.util.List;

public interface TopWordsService {
    List<WordEntry> getTopWords(int count);
}
