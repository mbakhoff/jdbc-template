package app;

import java.sql.Connection;

public interface CommandHandler {

  void handle(Connection conn, String command) throws Exception;
}
