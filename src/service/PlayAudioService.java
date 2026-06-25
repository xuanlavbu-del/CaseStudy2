package service;

import java.awt.Desktop;
import java.io.File;

public class PlayAudioService {

    public static void play(String filePath) {

        try {

            File file = new File(filePath);

            if (!file.exists()) {

                System.out.println(
                        "Audio file not found!");

                return;
            }

            Desktop.getDesktop().open(file);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}