import controller.*;
import service.FileStorageService;

import java.util.Scanner;

public class Main {

    public static void main(
            String[] args) {

        FileStorageService storage =
                new FileStorageService();

        storage.loadAllWords();

        Scanner scanner =
                new Scanner(System.in);

        System.out.print(
                "Action: ");

        String input =
                scanner.nextLine();

        RequestParser parser =
                new RequestParser();

        Request request =
                parser.parse(input);

        ActionController controller =
                new ActionController();

        controller.handle(
                request);
    }
}