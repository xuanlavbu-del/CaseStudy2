package command;

import controller.Request;
import entity.Word;
import service.DictionaryService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadAudioCommand
        implements Command {

    @Override
    public void execute(
            Request request) {

        try {

            String keyword =
                    request.getKeyword();

            String sourcePath =
                    request.getParams().get(0);

            Word word =
                    DictionaryService
                            .getInstance()
                            .lookup(keyword);

            if(word == null){

                System.out.println(
                        "Word not found!");

                return;
            }

            File folder =
                    new File("audio");

            if(!folder.exists()){

                folder.mkdirs();
            }

            File source =
                    new File(sourcePath);

            File target =
                    new File(
                            "audio/"
                                    + keyword
                                    + ".mp3");

            Files.copy(
                    source.toPath(),
                    target.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            word.setAudioFile(
                    target.getPath());

            System.out.println(
                    "Audio uploaded!");

        } catch(Exception e){

            e.printStackTrace();
        }
    }
}