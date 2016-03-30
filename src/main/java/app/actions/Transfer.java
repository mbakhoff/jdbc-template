package app.actions;

import app.CommandHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command for transferring money between accounts
 * Example: "transfer Taavi Märt 7.20"
 */
public class Transfer implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("transfer"))
      return;
    String[] args = command.split(" ");
    transfer(conn, args[1], args[2], new BigDecimal(args[3]));
  }

  private void transfer(Connection conn, String from, String to, BigDecimal sum) throws SQLException {
    conn.setAutoCommit(false);
    try (PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE holder_name = ? AND balance >= ?")) {
      // withdraw from
      ps.setBigDecimal(1, sum.negate());
      ps.setString(2, from);
      ps.setBigDecimal(3, sum);
      if (ps.executeUpdate() == 0) {
        System.out.println("no such account or not enough funds: " + from);
        conn.rollback();
        return;
      }

      // deposit to
      ps.setBigDecimal(1, sum);
      ps.setString(2, to);
      ps.setBigDecimal(3, BigDecimal.ZERO);
      if (ps.executeUpdate() == 0) {
        System.out.println("no such account: " + to);
        conn.rollback();
        return;
      }

      conn.commit();
      System.out.println("transferred " + sum + " from " + from + " to " + to);
    } catch (Exception e) {
      conn.rollback();
      throw e;
    } finally {
      conn.setAutoCommit(true);
    }
  }
}
