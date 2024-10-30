DELETE FROM users;

-- Очистка таблицы
INSERT INTO
    users (
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
        '123e4567-e89b-12d3-a456-426614174000', -- UUID
        'Oleg',
        'olegbobrik@urk.ua',
        'exsensesium',
        TRUE,
        TRUE,
        TRUE,
        '2014-10-21 12:00:00', -- registrationDate
        '2024-10-25 12:00:00', -- lastEnter
        '2024-10-30 12:00:00' -- lastUpdate
    ),
    (
        '123e4567-e89b-12d3-a456-426614174001', -- UUID
        'Kalok',
        'kalak@mail.ru',
        'jahan',
        TRUE,
        TRUE,
        FALSE,
        '2024-09-20 12:00:00', -- registrationDate
        '2024-10-28 12:00:00', -- lastEnter
        '2024-10-30 12:00:00' -- lastUpdate
    ),
    (
        '123e4567-e89b-12d3-a456-426614174002', -- UUID
        'Vladick',
        'vladed@gmail.com',
        'elvlado',
        TRUE,
        FALSE,
        TRUE,
        '2024-07-22 12:00:00', -- registrationDate
        '2024-10-29 12:00:00', -- lastEnter
        '2024-10-30 12:00:00' -- lastUpdate
    );