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
        System.out.println("==================================");
        System.out.println("DICTIONARY COMMANDS");

        System.out.println("lookup <word>");
        System.out.println("define <word> <type>");
        System.out.println("drop <word>");
        System.out.println("export");
        System.out.println("==================================");
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