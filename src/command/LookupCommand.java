package command;

import controller.Request;
import entity.Definition;
import entity.Word;
import service.DictionaryService;
import service.PlayAudioService;

import java.util.List;
import java.util.Scanner;

public class LookupCommand implements Command {

    @Override
    public void execute(Request request) {

        DictionaryService service = DictionaryService.getInstance();
        String keyword = request.getKeyword();

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Keyword is empty!");
            return;
        }

        Word word = service.lookup(keyword.trim());
        Scanner scanner = new Scanner(System.in);

        // ===== SEARCH NEAR WORD =====
        if (word == null) {

            List<Word> suggestions = service.searchNear(keyword);

            if (suggestions.isEmpty()) {
                System.out.println("Word not found!");
                return;
            }

            System.out.println("Found words:");

            for (int i = 0; i < suggestions.size(); i++) {

                Word w = suggestions.get(i);

                String type = "unknown";

                if (w.getDefinitions() != null && !w.getDefinitions().isEmpty()) {
                    type = w.getDefinitions().get(0).getType(); // FIX getFirst()
                }

                System.out.println((i + 1) + ". " + w.getKeyword() + " - " + type);
            }

            // ===== SELECT WORD (SAFE LOOP) =====
            while (true) {

                System.out.print("Choose (0 to cancel): ");

                String input = scanner.nextLine();

                int choice;

                try {
                    choice = Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Invalid number!");
                    continue;
                }

                if (choice == 0) {
                    return;
                }

                if (choice < 1 || choice > suggestions.size()) {
                    System.out.println("Invalid choice!");
                    continue;
                }

                word = suggestions.get(choice - 1);
                break;
            }
        }

        // ===== DISPLAY WORD =====
        System.out.println("\n@" + word.getKeyword());

        if (word.getDefinitions() != null) {

            for (Definition d : word.getDefinitions()) {

                System.out.println("* " + d.getType());
                System.out.println("- " + d.getContent());

                if (d.getSentence() != null && !d.getSentence().isEmpty()) {
                    System.out.println("= " + d.getSentence());
                    System.out.println("+ " + d.getSentenceMeaning());
                }

                System.out.println();
            }
        }

        // ===== AUDIO =====
        System.out.println("Press 1 to play pronunciation");

        String key = scanner.nextLine();

        if (key.equalsIgnoreCase("1")) {

            if (word.getAudioFile() != null && !word.getAudioFile().isEmpty()) {
                PlayAudioService.play(word.getAudioFile());
            } else {
                System.out.println("No audio file!");
            }
        }
    }
}