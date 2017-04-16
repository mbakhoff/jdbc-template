# Account Manager application

## Database access in Java

Most applications need to store and access data at some point.
A popular way to store data is to use a database.
Why use a database instead of a text file?
* built-in support for concurrency - many users can read/write at the same time
* better fault tolerance - databases have ways to ensure the data is not corrupt even in case of power failures etc.
* better data integrity - the database can enforce rules on data, e.g. references between data tables, uniqueness, etc.
* transaction support - make a set of changes, so that either everything is saved or nothing is saved in case of failure
* mad performance due to use of database indexes and highly optimized algorithms

The data in a database is organized into tables, consisting of columns and rows.
Most databases use SQL (structured query language) to express queries and commands.
SQL has some standardized syntax, but most databases use a slightly different dialect of SQL.
When using a database, always consult with your database's user manual.

How does one use a database in Java:
* choose a database to use
* add the database driver to classpath (maven dependency)
* use the DriverManager class to connect to the database
* execute database statements to query/update the database
* close the connection when done

Here are some examples of SQL queries:
* `SELECT id, name, email FROM students WHERE id = 'A12345';`
* `INSERT INTO students(id, name) VALUES ('A12345', 'Märt B');`
* `UPDATE students SET email = 'example@example.com' WHERE id = 'A12345';`

Here's how one could use the JDBC (Java DataBase Connectivity) API to insert a new student into the database:
```
void addStudent(Connection conn, String id, String name) throws SQLException {
  conn.prepareStatement("INSERT INTO students(id, name) VALUES ('" + id + "', '" + name + "');").executeUpdate();
}
```
The above code works (sometimes) and also contains one of the most popular security vulnerabilities of all times - SQL injection.
What happens if the value of the id is `0` and name parameter is `lol'); DELETE FROM students; --`?
The resulting SQL query would look like `INSERT INTO students(id, name) VALUES ('0', 'lol'); DELETE FROM students; --');`.
The database would happily first run the INSERT command and then delete the entire table with the DELETE command.
Here's a small list of epic fails caused by this bug: http://codecurmudgeon.com/wp/sql-injection-hall-of-shame/

How to avoid SQL injections?
The easiest way is to let the database driver escape the parameters for you.
Use the PreparedStatement parameters like this:
```
void addStudent(Connection conn, String id, String name) throws SQLException {
  PreparedStatement ps = conn.prepareStatement("INSERT INTO students(id, name) VALUES (?, ?);");
  ps.setString(1, id);
  ps.setString(2, name);
  ps.executeUpdate();
}
```
Key points:
* use the parameter placeholder `?` where a value needs to be inserted
* set the values using the set methods (note that the indexes start from 1)
* never use string concatenation to add values into a SQL query (and call out others for doing so)

What's that "transaction support" thing mentioned earlier?
A transaction is a set of commands that should be executed together atomically.
Either all commands or none of the commands complete.
When an error occurs at some command, then earlier changes from that transaction are automatically reverted.
The changes made by the transaction's commands become visible to the other database users all at once, when the transaction completes.
Other users cannot see the in-between state when the database runs multiple commands.

An example of transaction: when selling a car, the car first must be removed from the old owner's list of cars and then added to the new owner's list of cars.
Adding the car to the new owner's list can fail and then the car would be without an owner.
When the operations are grouped into a transaction, then the transaction would automatically take care of restoring the old state.

Here's how this works with JDBC:
```
private void sellCar(Connection conn, ...) throws SQLException {
  conn.setAutoCommit(false); // default is to run each command as a separate transaction
  try {
    // delete car from the old owner
    // register car to the new owner
    conn.commit(); // make changes visible and save to disk
  } catch (Exception e) {
    conn.rollback(); // revert all changes from this transaction
    throw e;
  } finally {
    conn.setAutoCommit(true); // restore what we changed
  }
}
```

## Exercise

This repository is a template for a simple bank account manager application.
The template already starts up the database and parses some of the command line stuff.
You will need to implement the following operations:

 - adding an account
 - displaying account balance
 - depositing money
 - withdrawing money
 - transferring money between accounts

Some notes:
* The application uses an embedded H2 database (http://h2database.com).
  You don't need to install anything - just start the application and the database will start when you connect to it.
* You only need to change the classes in `app.actions` package.
* Use java.math.BigDecimal for working with currency, otherwise you may run into weird rounding issues.
* Read the src/main/resources/database-setup.sql to see what the database tables look like.
* If you get stuck with writing SQL, see the sql-spoilers.txt file. Try to solve it on your own, first.
