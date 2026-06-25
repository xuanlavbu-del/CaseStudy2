package command;

import controller.Request;
import entity.Definition;
import entity.Word;
import service.DictionaryService;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ExportCommand implements Command {

    @Override
    public void execute(Request request) {

        String fileName = request.getKeyword();

        try (Writer writer =
                     new OutputStreamWriter(
                             new FileOutputStream(fileName),
                             StandardCharsets.UTF_8)) {

            for (Word word : DictionaryService.getInstance()
                    .getDictionary()
                    .values()) {

                writer.write("@" + word.getKeyword() + "\n");

                for (Definition d : word.getDefinitions()) {

                    writer.write("- " + d.getContent() + "\n");

                    if (d.getSentence() != null && !d.getSentence().isEmpty()) {
                        writer.write("= " + d.getSentence() + "\n");
                        writer.write("+ " + d.getSentenceMeaning() + "\n");
                    }
                }

                writer.write("\n");
            }

            System.out.println("Export done!");

        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}