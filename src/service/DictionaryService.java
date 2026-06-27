package service;

import entity.Word;

import java.util.*;

public class DictionaryService {

    private static volatile DictionaryService instance;

    private Map<String, Word> dictionary = new TreeMap<>();

    // ===== SINGLETON SAFE =====
    public static DictionaryService getInstance() {
        if (instance == null) {
            synchronized (DictionaryService.class) {
                if (instance == null) {
                    instance = new DictionaryService();
                }
            }
        }
        return instance;
    }

    private DictionaryService() {}

    // ===== NORMALIZE KEY =====
    private String normalize(String keyword) {
        return keyword == null ? "" : keyword.trim().toLowerCase();
    }

    // ===== ADD WORD =====
    public void addWord(Word word) {
        if (word == null || word.getKeyword() == null) return;

        dictionary.put(
                normalize(word.getKeyword()),
                word
        );
    }

    // ===== LOOKUP (FIXED) =====
    public Word lookup(String keyword) {
        return dictionary.get(normalize(keyword));
    }

    // ===== SEARCH NEAR (IMPROVED) =====
    public List<Word> searchNear(String keyword) {

        List<Word> result = new ArrayList<>();
        String key = normalize(keyword);

        if (key.isEmpty()) return result;

        for (Word word : dictionary.values()) {

            String current = normalize(word.getKeyword());


            if (current.startsWith(key)) {
                result.add(word);
                continue;
            }


            if (current.contains(key)) {
                result.add(word);
            }
        }

        return result;
    }


    public Map<String, Word> getDictionary() {
        return Collections.unmodifiableMap(dictionary);
    }
}