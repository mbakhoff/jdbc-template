package app;

import java.sql.Connection;

public interface CommandHandler {

  /**
   * Tries to process the provided command.
   * If the command is not supported by this CommandHandler, then false is returned.
   * Otherwise the command is executed on the connection and true is returned.
   */
  boolean handle(Connection conn, String command) throws Exception;
}
