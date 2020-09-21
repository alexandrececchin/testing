INSERT INTO ACCOUNT(ID, USERNAME, ACCOUNT_NUMBER, DEFAULT_ACCOUNT, LAST_MODIFIED, CREATED_AT)
values (0, 'user', '123456789', TRUE, 12345, 12345);
INSERT INTO address
VALUES (0, 12345, 12345, 0, 'Palo Alto', 'United States', 'CA', '3495 Deer Creek Road', '', '94304');
INSERT INTO account_addresses
VALUES (0, 0);
INSERT INTO credit_card
VALUES (0, 12345, 12345, '1234567801234567', 0);
INSERT INTO account_credit_cards
VALUES (0, 0);
INSERT INTO customer
VALUES (0, 12345, 12345, 'john.doe@example.com', 'John', 'Doe', 0);