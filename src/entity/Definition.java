package entity;

public class Definition {

    private String type;
    private String content;
    private String sentence;
    private String sentenceMeaning;

    // ===== CONSTRUCTOR =====
    public Definition(String type,
                      String content,
                      String sentence,
                      String sentenceMeaning) {

        this.type = type;
        this.content = content;
        this.sentence = sentence;
        this.sentenceMeaning = sentenceMeaning;
    }

    // ===== GETTERS =====
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

    // ===== OPTIONAL SETTERS (for future edit feature) =====
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

    // ===== SAFE CHECK METHODS =====
    public boolean hasExample() {
        return sentence != null && !sentence.trim().isEmpty();
    }

    public boolean hasMeaning() {
        return sentenceMeaning != null && !sentenceMeaning.trim().isEmpty();
    }

    // ===== CLEAN DISPLAY (OPTIONAL UTILITY) =====
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