package entity;

public class Definition {

    private String type;
    private String content;
    private String sentence;
    private String sentenceMeaning;

    public String getAudio() {
        return audio;
    }

    private String audio;

    public Definition(String type,
                      String content,
                      String sentence,
                      String sentenceMeaning,
                      String audio) {

        this.type = type;
        this.content = content;
        this.sentence = sentence;
        this.sentenceMeaning = sentenceMeaning;
        this.audio = audio;
    }


    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getSentence() {
        return sentence;
    }

    public String getSentenceMeaning() {
        return sentenceMeaning;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setSentenceMeaning(String sentenceMeaning) {
        this.sentenceMeaning = sentenceMeaning;
    }


    public boolean hasExample() {
        return sentence != null && !sentence.trim().isEmpty();
    }

    public boolean hasMeaning() {
        return sentenceMeaning != null && !sentenceMeaning.trim().isEmpty();
    }


    public String format() {

        StringBuilder sb = new StringBuilder();

        sb.append("- ").append(content);

        if (hasExample()) {
            sb.append("\n= ").append(sentence);

            if (hasMeaning()) {
                sb.append("\n+ ").append(sentenceMeaning);
            }
        }

        return sb.toString();
    }
}