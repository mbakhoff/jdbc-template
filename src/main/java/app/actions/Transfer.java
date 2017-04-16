package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for transferring money between accounts
 * Example: "transfer Taavi Märt 7.20"
 */
public class Transfer implements CommandHandler {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    // this task requires you to use transactions
    // use the return value of executeUpdate to see if the commands were successful
    return false;  // TODO: implement
  }
}
