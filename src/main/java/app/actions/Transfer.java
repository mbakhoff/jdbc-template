package app.actions;

import app.Processor;

import java.sql.Connection;

/**
 * Command for transferring money between accounts
 * Example: "transfer Taavi Märt 7.20"
 */
public class Transfer implements Processor {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    return false;  // TODO: implement
  }
}
