package app.actions;

import app.CommandHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command for depositing money to an account
 * Example: "deposit Taavi 20.5"
 */
public class Deposit implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("deposit"))
      return;
    String[] args = command.split(" ");
    deposit(conn, args[1], new BigDecimal(args[2]));
  }

  private void deposit(Connection conn, String name, BigDecimal sum) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE holder_name = ?")) {
      ps.setBigDecimal(1, sum);
      ps.setString(2, name);
      if (ps.executeUpdate() > 0) {
        System.out.println("deposited " + sum + " to " + name);
      } else {
        System.out.println("no such account");
      }
    }
  }
}
