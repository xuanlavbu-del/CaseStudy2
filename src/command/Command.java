package command;

import controller.Request;

public interface Command {

    void execute(Request request);
}