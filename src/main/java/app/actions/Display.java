package app.actions;

import app.CommandHandler;

import java.sql.Connection;

/**
 * Command for displaying the account balance
 * Example: "display Taavi"
 */
public class Display implements CommandHandler {
  @Override
  public boolean handle(Connection conn, String command) throws Exception {
    // use PreparedStatement#executeQuery and the returned ResultSet
    // see ResultSet#next, ResultSet#getString, ResultSet#getBigDecimal
    return false;  // TODO: implement
  }
}
