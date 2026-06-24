package entity;

public class Definition {

    private String type;
    private String content;
    private String sentence;
    private String sentenceMeaning;

    public Definition(String type,
                      String content,
                      String sentence,
                      String sentenceMeaning) {

        this.type = type;
        this.content = content;
        this.sentence = sentence;
        this.sentenceMeaning = sentenceMeaning;
    }

    public String getType() {
        return type;
    }

    public String display() {

        StringBuilder sb = new StringBuilder();

        sb.append("- ").append(content);

        if(sentence != null &&
                !sentence.isEmpty()) {

            sb.append("\n= ")
                    .append(sentence);

            sb.append("\n+ ")
                    .append(sentenceMeaning);
        }

        return sb.toString();
    }
}