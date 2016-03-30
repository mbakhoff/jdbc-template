package app.actions;

import app.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Command for adding a new account
 * Example: "signup Aivar"
 */
public class Signup implements CommandHandler {
  @Override
  public void handle(Connection conn, String command) throws Exception {
    if (!command.startsWith("signup"))
      return;
    signup(conn, command.split(" ")[1]);
  }

  private void signup(Connection conn, String name) throws SQLException {
    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts (holder_name) VALUES (?)")) {
      ps.setString(1, name);
      if (ps.executeUpdate() > 0) {
        System.out.println("signed up " + name);
      } else {
        System.out.println("signup failed"); // unlikely. might get a constraint violation exception instead
      }
    }
  }
}
