package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for adding a new account
 * Example: "signup Aivar"
 */
public class Signup implements CommandHandler {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    return false;  // TODO: implement
  }
}
