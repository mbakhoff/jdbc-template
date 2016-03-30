package app.actions;

import app.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Command for displaying the account balance
 * Example: "display Taavi"
 */
public class Display implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("display"))
      return;
    display(conn, command.split(" ")[1]);
  }

  private void display(Connection conn, String name) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts WHERE holder_name = ?")) {
      ps.setString(1, name);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          System.out.println("account balance: " + rs.getBigDecimal("balance"));
        } else {
          System.out.println("no such account");
        }
      }
    }
  }
}
