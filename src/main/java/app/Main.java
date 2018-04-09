package app;

import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

  public static void main(String[] args) throws Exception {
    // start the database by connecting to it
    try (Connection connection = connectToDatabase()) {

      // run the commands from database-setup.sql
      // this creates the accounts table and adds some sample data
      try (Reader setupSql = readFromClasspath("database-setup.sql")) {
        RunScript.execute(connection, setupSql);
      }

      // run the interactive command line
      new AccountManager(connection).runInteractive();
    }
  }

  private static Connection connectToDatabase() throws Exception {
    // create a new database that is stored fully in the memory.
    // closing the application destroys the database.
    // http://h2database.com/html/features.html#in_memory_databases
    return DriverManager.getConnection("jdbc:h2:mem:");

    // ALTERNATIVE: create a new database on the disk (db/sample.db).
    // data is preserved when the application is restarted.
    //return DriverManager.getConnection("jdbc:h2:db/sample.db");
  }

  private static InputStreamReader readFromClasspath(String name) throws Exception {
    ClassLoader cl = Main.class.getClassLoader();
    return new InputStreamReader(cl.getResourceAsStream(name), "UTF-8");
  }
}
