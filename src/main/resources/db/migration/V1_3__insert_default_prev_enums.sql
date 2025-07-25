-- Insert transaction types
INSERT INTO transaction_types (name, is_income)
VALUES
    -- Income types
    ('SALARY', true),
    ('GIFT', true),
    ('INVESTMENT', true),
    ('INCOME', true),

    -- Expense types
    ('GROCERIES', false),
    ('GAS', false),
    ('ELECTRICITY', false),
    ('WATER', false),
    ('RENT', false),
    ('ENTERTAINMENT', false),
    ('HEALTHCARE', false),
    ('EDUCATION', false),
    ('TRANSPORTATION', false),
    ('EXPENSE', false)
ON CONFLICT (name) DO NOTHING;

-- Insert payment methods
INSERT INTO payment_methods (name)
VALUES
    ('CASH'),
    ('CARD')
ON CONFLICT (name) DO NOTHING;