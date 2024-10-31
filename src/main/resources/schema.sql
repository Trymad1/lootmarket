CREATE TABLE
    IF NOT EXISTS users (
        id UUID PRIMARY KEY,
        name VARCHAR(25) NOT NULL UNIQUE,
        mail VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(25) NOT NULL,
        place_service BOOL NOT NULL,
        place_review BOOL NOT NULL,
        use_service BOOL NOT NULL,
        registration_date TIMESTAMP NOT NULL,
        last_enter TIMESTAMP NOT NULL,
        last_update TIMESTAMP NOT NULL
    );

CREATE TABLE
    IF NOT EXISTS games (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        name VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE
    IF NOT EXISTS services_category (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        name VARCHAR(50) NOT NULL UNIQUE,
        game_id INT NOT NULL,
        FOREIGN KEY (game_id) REFERENCES games (id) ON DELETE CASCADE
    );

CREATE TABLE
    IF NOT EXISTS services (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        user_id UUID NOT NULL,
        service_category_id INT NOT NULL,
        brief_desc VARCHAR(50) NOT NULL,
        detailed_desc TEXT NOT NULL,
        quantity INT,
        price INT NOT NULL,
        create_date TIMESTAMP NOT NULL,
        update_date TIMESTAMP NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users (id),
        FOREIGN KEY (service_category_id) REFERENCES services_category (id)
    );

CREATE TABLE
    IF NOT EXISTS messages (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        sender_id UUID NOT NULL,
        recipient_id UUID NOT NULL,
        message_text VARCHAR(255) NOT NULL,
        send_date TIMESTAMP NOT NULL,
        FOREIGN KEY (sender_id) REFERENCES users (id),
        FOREIGN KEY (recipient_id) REFERENCES users (id)
    );

CREATE TABLE
    IF NOT EXISTS payment_systems (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        name VARCHAR(20) NOT NULL
    );

CREATE TABLE
    IF NOT EXISTS payment_details (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        user_id UUID NOT NULL,
        payment_system_id INT NOT NULL,
        details VARCHAR(20) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users (id),
        FOREIGN KEY (payment_system_id) REFERENCES payment_systems (id)
    );

CREATE TABLE
    IF NOT EXISTS deal_status (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        name VARCHAR(20) NOT NULL
    );

CREATE TABLE
    IF NOT EXISTS deals (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        service_id INT NOT NULL,
        user_buyer_id UUID NOT NULL,
        payment_system_id INT NOT NULL,
        deal_status_id INT NOT NULL,
        quantity_purchased INT,
        deal_start TIMESTAMP NOT NULL,
        deal_end TIMESTAMP,
        FOREIGN KEY (service_id) REFERENCES services (id),
        FOREIGN KEY (user_buyer_id) REFERENCES users (id),
        FOREIGN KEY (payment_system_id) REFERENCES payment_systems (id),
        FOREIGN KEY (deal_status_id) REFERENCES deal_status (id)
    );

CREATE TABLE
    IF NOT EXISTS reviews (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        user_id UUID NOT NULL,
        deal_id INT NOT NULL,
        grade INT NOT NULL,
        comment VARCHAR(255) NOT NULL,
        DATE TIMESTAMP NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users (id),
        FOREIGN KEY (deal_id) REFERENCES deals (id)
    );