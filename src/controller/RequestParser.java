package controller;

import java.util.ArrayList;
import java.util.List;

public class RequestParser {

    public Request parse(String input) {

        String[] tokens =
                input.trim().split("\\s+");

        String action = tokens[0];

        String keyword =
                tokens[tokens.length - 1];

        List<String> params =
                new ArrayList<>();

        for(int i = 1;
            i < tokens.length - 1;
            i++) {

            params.add(tokens[i]);
        }

        return new Request(
                action,
                keyword,
                params);
    }
}