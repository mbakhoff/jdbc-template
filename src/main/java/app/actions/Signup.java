package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for adding a new account
 * Example: "signup Aivar"
 */
public class Signup implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("signup"))
      return;
    // TODO: implement
  }
}
