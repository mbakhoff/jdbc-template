package app.actions;

import app.Processor;

import java.sql.Connection;

/**
 * Command for displaying the account balance
 * Example: "display Taavi"
 */
public class Display implements Processor {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    return false;  // TODO: implement
  }
}
