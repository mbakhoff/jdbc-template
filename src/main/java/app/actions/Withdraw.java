package app.actions;

import app.CommandHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command for withdrawing money from an account
 * Example: "withdraw Märt 10.0"
 */
public class Withdraw implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("withdraw"))
      return;
    String[] args = command.split(" ");
    withdraw(conn, args[1], new BigDecimal(args[2]));
  }

  private void withdraw(Connection conn, String name, BigDecimal sum) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE holder_name = ? AND balance > ?")) {
      ps.setBigDecimal(1, sum);
      ps.setString(2, name);
      ps.setBigDecimal(3, sum);
      if (ps.executeUpdate() > 0) {
        System.out.println("withdrew " + sum + " from " + name);
      } else {
        System.out.println("no such account or not enough funds");
      }
    }
  }
}
