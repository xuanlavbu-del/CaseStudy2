package command;

import controller.Request;
import service.DictionaryService;
import service.FileStorageService;

public class DropCommand
        implements Command {

    @Override
    public void execute(
            Request request) {

        DictionaryService
                .getInstance()
                .getDictionary()
                .remove(
                        request.getKeyword());

        FileStorageService.saveAll();

        System.out.println(
                "@"
                        + request.getKeyword()
                        + " dropped!");
    }
}