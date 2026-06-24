package entity;

import java.util.LinkedList;

public class Word {

    private String keyword;

    private LinkedList<Definition>
            definitions =
            new LinkedList<>();

    public Word(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public LinkedList<Definition>
    getDefinitions() {
        return definitions;
    }

    public void addDefinition(
            Definition definition) {

        definitions.add(definition);
    }
}