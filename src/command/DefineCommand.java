package command;

import controller.Request;
import entity.Definition;
import entity.Word;
import service.DictionaryService;
import service.FileStorageService;

import java.util.Scanner;

public class DefineCommand
        implements Command {

    @Override
    public void execute(
            Request request) {

        Scanner scanner =
                new Scanner(System.in);

        DictionaryService service =
                DictionaryService
                        .getInstance();

        Word word =
                service.lookup(
                        request.getKeyword());

        if(word == null) {

            word =
                    new Word(
                            request.getKeyword());

            service.addWord(word);

            System.out.println(
                    "@"
                            + request.getKeyword()
                            + " created!");
        }

        System.out.print(
                "Definition: ");

        String meaning =
                scanner.nextLine();

        System.out.print(
                "Sentence: ");

        String sentence =
                scanner.nextLine();

        System.out.print(
                "Sentence meaning: ");

        String sentenceMeaning =
                scanner.nextLine();

        String type =
                request.getParams()
                        .get(0);

        Definition definition =
                new Definition(
                        type,
                        meaning,
                        sentence,
                        sentenceMeaning);

        word.addDefinition(
                definition);

        FileStorageService.saveAll();

        System.out.println(
                "Saved!");
    }
}