-- Initial data for the account manager

-- See H2 SQL docs for details:
-- http://h2database.com/html/grammar.html
-- http://h2database.com/html/datatypes.html

CREATE TABLE accounts (
  holder_name VARCHAR(255) NOT NULL PRIMARY KEY,
  balance DECIMAL(20,2) DEFAULT 0.0
);

INSERT INTO accounts(holder_name, balance) VALUES ('Märt', 39.99);
INSERT INTO accounts(holder_name, balance) VALUES ('Taavi', 159.49);
