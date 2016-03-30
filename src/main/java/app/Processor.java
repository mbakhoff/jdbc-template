package app;

import java.sql.Connection;

public interface Processor {
  boolean handle(Connection conn, String command) throws Exception;
}
