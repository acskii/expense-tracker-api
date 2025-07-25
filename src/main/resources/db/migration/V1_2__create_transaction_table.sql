-- Create transactions table
CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT DEFAULT '',
    location VARCHAR(255) DEFAULT '',
    created_on TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    amount NUMERIC(12, 3) NOT NULL
        CHECK (amount > 0),
    profit BOOLEAN NOT NULL,
    user_id UUID NOT NULL,
    type_id INTEGER NOT NULL,
    method_id INTEGER NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES transaction_types(id),
    FOREIGN KEY (method_id) REFERENCES payment_methods(id)
);

-- Create indexes for better performance
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_type ON transactions(type_id);
CREATE INDEX idx_transactions_method ON transactions(method_id);