package command;

import controller.Request;
import entity.Word;
import service.DictionaryService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadAudioCommand implements Command {

    @Override
    public void execute(Request request) {

        try {
            String keyword = request.getKeyword();

            if (request.getParams() == null || request.getParams().isEmpty()) {
                System.out.println("Vui lòng nhập đường dẫn file audio!");
                return;
            }

            String sourcePath = request.getParams().get(0);

            Word word = DictionaryService.getInstance().lookup(keyword);

            if (word == null) {
                System.out.println("Word not found!");
                return;
            }

            File source = new File(sourcePath);

            if (!source.exists()) {
                System.out.println("File audio không tồn tại!");
                return;
            }

            // tạo folder audio nếu chưa có
            File folder = new File("audio");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // lấy extension gốc (.mp3, .wav,...)
            String fileName = source.getName();
            String extension = "";

            int i = fileName.lastIndexOf(".");
            if (i > 0) {
                extension = fileName.substring(i);
            }

            // lưu theo từ vựng
            File target = new File("audio/" + keyword + extension);

            Files.copy(
                    source.toPath(),
                    target.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            // gán audio vào word
            word.setAudioFile(target.getPath());

            System.out.println("Upload audio thành công: " + target.getPath());

        } catch (Exception e) {
            System.out.println("Upload thất bại: " + e.getMessage());
        }
    }
}