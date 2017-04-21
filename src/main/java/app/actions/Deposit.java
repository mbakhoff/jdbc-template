package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for depositing money to an account
 * Example: "deposit Taavi 20.5"
 */
public class Deposit implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("deposit"))
      return;
    // TODO: implement
  }
}
