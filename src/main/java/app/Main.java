package app;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    try (Connection connection = getConnection();
         Scanner console = new Scanner(System.in)) {
      loadInitialData(connection);
      new AccountManager(connection).run(console);
    }
  }

  private static Connection getConnection() throws Exception {
    // the connection url is a database specific string that describes
    // how to connect to the database (ip address, username, password, etc)
    // H2 documentation is here http://h2database.com/html/cheatSheet.html
    
    // this one uses an in-memory database.
    // closing the connection destroys the database.
    // http://h2database.com/html/features.html#in_memory_databases
    String connectionUrl = "jdbc:h2:mem:";

    // use this to keep the database on the disk.
    // choose any location; h2 will create the file.
    //String connectionUrl = "jdbc:h2:/path/to/database.h2";

    return DriverManager.getConnection(connectionUrl);
  }

  private static void loadInitialData(Connection connection) throws Exception {
    try (Reader reader = new InputStreamReader(Main.class.getResourceAsStream("/database-setup.sql"), "UTF-8")) {
      RunScript.execute(connection, reader);
    }
  }
}
