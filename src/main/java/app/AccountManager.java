package app;

import app.actions.Deposit;
import app.actions.Display;
import app.actions.Signup;
import app.actions.Transfer;
import app.actions.Withdraw;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
  private final Connection connection;
  private final List<CommandHandler> commandHandlers;

  public AccountManager(Connection connection) {
    this.connection = connection;
    this.commandHandlers = loadCommandHandlers();
  }

  public void run(Scanner console) throws Exception {
    while (true) {
      System.out.print("manager > ");
      String command = console.nextLine();
      if (command.equals(":q"))
        break;
      handleCommand(command);
    }
  }

  private void handleCommand(String command) throws Exception {
    for (CommandHandler commandHandler : commandHandlers) {
      if (commandHandler.handle(connection, command))
        return;
    }
    System.out.println("unknown command");
  }

  private List<CommandHandler> loadCommandHandlers() {
    return Arrays.asList(
        new Signup(),
        new Display(),
        new Deposit(),
        new Withdraw(),
        new Transfer()
    );
  }
}
