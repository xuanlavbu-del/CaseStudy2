package command;

import controller.Request;
import entity.Definition;
import entity.Word;
import service.DictionaryService;
import service.FileStorageService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class DefineCommand implements Command {

    private static final String AUDIO_FOLDER = "audio";

    @Override
    public void execute(Request request) {

        Scanner scanner = new Scanner(System.in);

        DictionaryService service = DictionaryService.getInstance();

        Word word = service.lookup(request.getKeyword());

        if (word == null) {
            word = new Word(request.getKeyword());
            service.addWord(word);

            System.out.println("@" + request.getKeyword() + " created!");
        }

        System.out.print("Definition: ");
        String meaning = scanner.nextLine();

        System.out.print("Sentence: ");
        String sentence = scanner.nextLine();

        System.out.print("Sentence meaning: ");
        String sentenceMeaning = scanner.nextLine();


        String type;

        if (request.getParams() == null || request.getParams().isEmpty()) {
            System.out.print("Type (noun/verb/adjective...): ");
            type = scanner.nextLine();
        } else {
            type = request.getParams().get(0);
        }

        // =========================
        // 🔊 THÊM AUDIO INPUT
        // =========================
        System.out.print("Audio file path (vd: C:\\\\audio\\\\test.mp3): ");
        String audioPath = scanner.nextLine();

        String audioFileName = null;

        try {
            if (audioPath != null && !audioPath.isEmpty()) {

                File folder = new File(AUDIO_FOLDER);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                Path source = Paths.get(audioPath);
                audioFileName = source.getFileName().toString();

                Path target = Paths.get(AUDIO_FOLDER + File.separator + audioFileName);

                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println("Cannot upload audio: " + e.getMessage());
        }

        // =========================
        // CREATE DEFINITION
        // =========================
        Definition definition = new Definition(
                type,
                meaning,
                sentence,
                sentenceMeaning,
                audioFileName
        );

        word.addDefinition(definition);

        FileStorageService.saveAll();

        System.out.println("Saved!");
    }
}