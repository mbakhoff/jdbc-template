package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for withdrawing money from an account
 * Example: "withdraw Märt 10.0"
 */
public class Withdraw implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("withdraw"))
      return;
    // TODO: implement
  }
}
