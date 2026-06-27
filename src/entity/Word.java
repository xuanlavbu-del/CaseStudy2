package entity;

import java.util.LinkedList;
import java.util.List;

public class Word {

    private String keyword;
    private String audioFile;

    private LinkedList<Definition> definitions = new LinkedList<>();


    public Word(String keyword) {
        this.keyword = keyword;
    }

    public Word(String keyword, String audioFile) {
        this.keyword = keyword;
        this.audioFile = audioFile;
    }


    public String getKeyword() {
        return keyword;
    }

    public String getAudioFile() {
        return audioFile;
    }


    public List<Definition> getDefinitions() {
        return definitions;
    }


    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }


    public void addDefinition(Definition definition) {
        if (definition != null) {
            definitions.add(definition);
        }
    }


    public boolean hasAudio() {
        return audioFile != null && !audioFile.trim().isEmpty();
    }

    public boolean hasDefinitions() {
        return !definitions.isEmpty();
    }
}