package service;

import entity.Definition;
import entity.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorageService {

    private static final String FILE_PATH =
            "database/dictionary.txt";

    /**
     * Nạp toàn bộ dữ liệu từ file txt
     */
    public void loadAllWords() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            System.out.println(
                    "Database not found: "
                            + FILE_PATH);

            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            DictionaryService service =
                    DictionaryService.getInstance();

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data =
                        line.split(";", -1);

                /*
                 * Format:
                 * keyword;
                 * audioFile;
                 * type;
                 * content;
                 * sentence;
                 * sentenceMeaning
                 */

                if (data.length < 4) {
                    continue;
                }

                String keyword = data[0];

                String audioFile =
                        data.length > 1
                                ? data[1]
                                : "";

                String type =
                        data.length > 2
                                ? data[2]
                                : "";

                String content =
                        data.length > 3
                                ? data[3]
                                : "";

                String sentence =
                        data.length > 4
                                ? data[4]
                                : "";

                String sentenceMeaning =
                        data.length > 5
                                ? data[5]
                                : "";

                Word word =
                        service.lookup(keyword);

                if (word == null) {

                    word =
                            new Word(keyword);

                    service.addWord(word);
                }

                word.setAudioFile(audioFile);

                Definition definition =
                        new Definition(
                                type,
                                content,
                                sentence,
                                sentenceMeaning,
                                audioFile);

                word.addDefinition(
                        definition);
            }

            System.out.println(
                    "Loaded "
                            + service
                            .getDictionary()
                            .size()
                            + " words");

        } catch (IOException e) {

            System.out.println(
                    "Load database failed!");

            e.printStackTrace();
        }
    }

    /**
     * Lưu toàn bộ dữ liệu xuống file
     */
    public static void saveAll() {

        try {

            File folder =
                    new File("database");

            if (!folder.exists()) {

                folder.mkdirs();
            }

            FileWriter writer =
                    new FileWriter(FILE_PATH);

            for (Word word :
                    DictionaryService
                            .getInstance()
                            .getDictionary()
                            .values()) {

                for (Definition definition :
                        word.getDefinitions()) {

                    String audioFile =
                            word.getAudioFile();

                    if (audioFile == null) {
                        audioFile = "";
                    }

                    writer.write(
                            word.getKeyword()
                                    + ";"
                                    + audioFile
                                    + ";"
                                    + definition.getType()
                                    + ";"
                                    + definition.getContent()
                                    + ";"
                                    + definition.getSentence()
                                    + ";"
                                    + definition.getSentenceMeaning()
                                    + "\n");
                }
            }

            writer.close();

            System.out.println(
                    "Database saved!");

        } catch (Exception e) {

            System.out.println(
                    "Save database failed!");

            e.printStackTrace();
        }
    }
}