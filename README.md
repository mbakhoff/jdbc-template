# Account Manager application

This a template for a simple account manager application. 
The template already starts up the database and parses some of the command line stuff. 
You will need to implement the following operations: 

 - adding an account
 - displaying account balance
 - depositing money
 - withdrawing money
 - transferring money between accounts (transactions!)

The application uses an embedded H2 database (http://h2database.com). 
You don't need to install anything - just start the application and the database will automatically start inside your JVM when you first connect to it. 

You'll be communicating with the database using Java's built-in database API called **JDBC** (*Java Database Connectivity*). 
Stanford has a mostly decent hands-on tutorial here: http://infolab.stanford.edu/~ullman/fcdb/oracle/or-jdbc.html 

The main tools you should be using: 

 - java.math.BigDecimal (better than Double for currency)
 - java.sql.Connection: prepareStatement, setAutoCommit, commit, rollback
 - java.sql.PreparedStatement: set*, executeUpdate, executeQuery 
 - java.sql.ResultSet: next, get*

Also, please read about how **not** to make SQL queries: http://bobby-tables.com/
