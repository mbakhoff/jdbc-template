package app;

import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
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
    Properties properties = new Properties();
    try (InputStream is = getResource("database.properties")) {
      properties.load(is);
    }

    String connectionUrl = properties.getProperty("url");
    if (connectionUrl == null)
      throw new RuntimeException("url missing in database.properties");
    return DriverManager.getConnection(connectionUrl, properties);
  }

  private static void loadInitialData(Connection connection) throws Exception {
    try (Reader reader = new InputStreamReader(getResource("database-setup.sql"), "UTF-8")) {
      RunScript.execute(connection, reader);
    }
  }

  private static InputStream getResource(String name) throws IOException {
    InputStream stream = Main.class.getClassLoader().getResourceAsStream(name);
    if (stream == null)
      throw new IOException(name + " not in classpath");
    return stream;
  }
}
