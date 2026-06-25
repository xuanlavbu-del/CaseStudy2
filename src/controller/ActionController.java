package controller;

import command.*;

import java.util.HashMap;
import java.util.Map;

public class ActionController {

    private Map<String, Command>
            commands =
            new HashMap<>();

    public ActionController() {

        commands.put(
                "lookup",
                new LookupCommand());

        commands.put(
                "define",
                new DefineCommand());

        commands.put(
                "drop",
                new DropCommand());

        commands.put(
                "export",
                new ExportCommand());
        commands.put(
                "upload-audio",
                new UploadAudioCommand());
    }

    public void handle(
            Request request) {

        Command command =
                commands.get(
                        request.getAction());

        if(command != null) {

            command.execute(
                    request);
        }
        else {

            System.out.println(
                    "Invalid action!");
        }
    }
}