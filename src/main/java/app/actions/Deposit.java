package app.actions;

import app.Processor;

import java.sql.Connection;

/**
 * Command for depositing money to an account
 * Example: "deposit Taavi 20.5"
 */
public class Deposit implements Processor {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    return false;  // TODO: implement
  }
}
