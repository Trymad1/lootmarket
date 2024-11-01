DELETE FROM users;

-- Очистка таблицы
INSERT INTO users (
        id,
        name,
        mail,
        password,
        place_service,
        place_review,
        use_service,
        registration_date,
        last_enter,
        last_update
    )
VALUES
    (
        '123e4567-e89b-12d3-a456-426614174000',
        'Oleg',
        'olegbobrik@urk.ua',
        'exsensesium',
        TRUE,
        TRUE,
        TRUE,
        '2014-10-21 12:00:00', 
        '2024-10-25 12:00:00', 
        '2024-10-30 12:00:00' 
    ),
    (
        '123e4567-e89b-12d3-a456-426614174001',
        'Kalok',
        'kalak@mail.ru',
        'jahan',
        TRUE,
        TRUE,
        FALSE,
        '2024-09-20 12:00:00',
        '2024-10-28 12:00:00',
        '2024-10-30 12:00:00' 
    ),
    (
        '123e4567-e89b-12d3-a456-426614174002', 
        'Vladick',
        'vladed@gmail.com',
        'elvlado',
        TRUE,
        FALSE,
        TRUE,
        '2024-07-22 12:00:00', 
        '2024-10-29 12:00:00', 
        '2024-10-30 12:00:00' 
    );

DELETE FROM games;
DELETE FROM services_category;
ALTER SEQUENCE games_id_seq RESTART WITH 1;
ALTER SEQUENCE services_category_id_seq RESTART WITH 1;

INSERT INTO games (name) VALUES ('dota'),('csgo'),('wow');
INSERT INTO services_category (name, game_id) VALUES
('accounts', 1),
('boosr', 1),
('reputation', 1),
('accounts', 2),
('trainig', 2),
('goldsell', 3),
('raidcomplete', 3);

