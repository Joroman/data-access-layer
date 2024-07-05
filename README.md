# data-access-layer
# create a user on DB
-- Drop user first if they exist
DROP USER if exists 'adminpayments'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'adminpayments'@'localhost' IDENTIFIED BY 'payments';

GRANT ALL PRIVILEGES ON * . * TO 'adminpayments'@'localhost';

# Database model definition

Certainly! Here's a refined set of tables for your payment API database with the additional features you mentioned:

1. Users:
+ id (INT PRIMARY KEY): Unique identifier for the user.
+ username (VARCHAR(255) UNIQUE): Username for login.
+ password (CHAR(60)): Hashed password for secure storage. (Important: Never store plain text passwords!)
+ email (VARCHAR(255) UNIQUE): User's email address (optional).
+ role (ENUM('admin', 'client')): User's role (admin or client).
+ created_at (DATETIME): Timestamp of when the user record was created.

2. Clients (Extends Users):
+ Inherit all columns from the Users table.
+ additional_info (TEXT): Optional field for storing additional client information not covered in Users.

3. PaymentProviders:
+ id (INT PRIMARY KEY): Unique identifier for the payment provider.
+ name (VARCHAR(255)): Name of the payment provider (e.g., Stripe, PayPal).
+ config (TEXT): Configuration details for the payment provider (securely store sensitive information).

4. Payments:
+ id (INT PRIMARY KEY): Unique identifier for the payment record.
+ client_id (INT FOREIGN KEY REFERENCES Clients(id)): Reference to the client who made the payment.
+ payment_provider_id (INT FOREIGN KEY REFERENCES PaymentProviders(id)): Reference to the payment provider used for the transaction.
+ payment_type (ENUM('bank', 'card')): Type of payment (bank transfer or card).
+ amount (DECIMAL): Amount of the payment.
+ currency (CHAR(3)): Currency code (e.g., USD, EUR).
+ status (ENUM('pending', 'succeeded', 'failed', 'cancelled')): Current status of the payment.
+ created_at (DATETIME): Timestamp of when the payment record was created.
+ updated_at (DATETIME): Timestamp of when the payment record was last updated.
+ payment_details (TEXT): Optional field for storing additional payment details specific to the provider (e.g., transaction ID, authorization code).

5. Transactions:
+ id (INT PRIMARY KEY): Unique identifier for the transaction.
+ payment_id (INT FOREIGN KEY REFERENCES Payments(id)): Reference to the payment this transaction belongs to.
+ amount (DECIMAL): Amount of this individual transaction (useful for installments).
+ transaction_type (ENUM('debit', 'credit')): Type of transaction (payment or refund).
+ created_at (DATETIME): Timestamp of when the transaction record was created.

6. Refunds:
+ id (INT PRIMARY KEY): Unique identifier for the refund record.
+ payment_id (INT FOREIGN KEY REFERENCES Payments(id)): Reference to the payment being refunded.
+ amount (DECIMAL): Amount refunded.
+ reason (TEXT): Reason for the refund (optional).
+ created_at (DATETIME): Timestamp of when the refund record was created.

Notes:
+ The Clients table inherits all columns from the Users table, making it an extension for storing additional client-specific information.
+ The Transactions table allows tracking individual transactions within a payment (e.g., installments).
+ The Refunds table helps manage refund details.
+ Remember to implement proper security measures for sensitive data like passwords and payment details.
