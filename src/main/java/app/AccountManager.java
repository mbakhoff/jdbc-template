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

  public void runInteractive() throws Exception {
    try (Scanner console = new Scanner(System.in)) {
      while (true) {
        System.out.print("manager > ");
        String command = console.nextLine();
        if (command.equals(":q"))
          break;
        for (CommandHandler commandHandler : commandHandlers) {
          commandHandler.handle(connection, command);
        }
      }
    }
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
