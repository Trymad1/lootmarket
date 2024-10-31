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

INSERT INTO games (id, name) VALUES (1, 'dota'),(2, 'csgo'),(3, 'wow');
INSERT INTO services_category (id, name, game_id) VALUES
(1, 'accounts', 1),
(2, 'boosr', 1),
(3, 'reputation', 1),
(4, 'accounts', 2),
(5, 'trainig', 2),
(6, 'goldsell', 3),
(7, 'raidcomplete', 3);

