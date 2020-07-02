package org.aidarkhanov.services;

import org.aidarkhanov.entities.WordEntry;

import java.util.List;

public interface TopWordsService {
    List<WordEntry> getTopWords(int count);
}
