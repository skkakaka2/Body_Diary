-- Active: 1767078624943@@127.0.0.1@3306@body_diary
INSERT INTO
    `user` (username, password)
SELECT 'admin', '$2a$10$MRBRGwIMwWL.i5aGOEjbIeptkuEyvAc97lOdv9XjZZKeYVdSLu7gm'
WHERE
    NOT EXISTS (
        SELECT 1
        FROM `user`
        WHERE
            username = 'admin'
    );