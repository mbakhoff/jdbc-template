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
  private final List<Processor> processors;

  public AccountManager(Connection connection) {
    this.connection = connection;
    this.processors = loadProcessors();
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
    for (Processor processor : processors) {
      if (processor.handle(connection, command))
        return;
    }
    System.out.println("unknown command");
  }

  private List<Processor> loadProcessors() {
    // check the enterprise branch for fun and profit
    return Arrays.asList(
        new Signup(),
        new Display(),
        new Deposit(),
        new Withdraw(),
        new Transfer()
    );
  }
}
