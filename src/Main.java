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
        System.out.println("action input:");
        System.out.println("1. lookup <word>");
        System.out.println("2. define <word> <type>");
        System.out.println("3. drop <word>");
        System.out.println("4. export");
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