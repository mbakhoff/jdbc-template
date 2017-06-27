package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for transferring money between accounts
 * Example: "transfer Taavi Märt 7.20"
 */
public class Transfer implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("transfer"))
      return;
    // REQUIREMENT: use a transaction
    // HINT: use the return value of executeUpdate to see if the commands were successful
    // HINT: use "some_column = some_column + ?" in the SQL UPDATE command
    // TODO: implement
  }
}
