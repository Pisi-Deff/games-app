INSERT INTO "dude"
(email, password_hash, display_name)
VALUES
('eerik@email.email', '$2a$10$8wpwBOsVRj7hfRtFuZcXEOwUGDRN.REAsHxvgzD03L4s.bZoEhKiG', 'Eerik'),
('adi@email.email', '$2a$10$8wpwBOsVRj7hfRtFuZcXEOwUGDRN.REAsHxvgzD03L4s.bZoEhKiG', 'Adrian'),
('conn@email.email', '$2a$10$8wpwBOsVRj7hfRtFuZcXEOwUGDRN.REAsHxvgzD03L4s.bZoEhKiG', 'Connah Fitzgerald Plattsworth');

INSERT INTO "tag"
(name)
VALUES
('Third-person'),
('First-person'),
('Shooter'),
('RTS'),
('RPG'),
('Arcade');

INSERT INTO "game"
(name, description, release_date)
VALUES
('Pong', 'PING! PONG!', '1972-11-29'),
('Wolfenstein: Enemy Territory', 'PEW PEW! ALLIES GOOD NAZIS BAD!', '2003-05-29'),
('Minecraft', 'Don''t dig straight down, ya willy', '2011-11-18'),
('Killing Floor', 'DIE, ZOMBERTS', '2009-05-14'),
('Killing Floor 2', 'DIE MORE, ZOMBERTS', '2016-11-18'),
('Cities: Skylines', NULL, '2015-03-10'),
('Grand Theft Auto V', 'Loading Screen Simulator 2015', '2015-04-14'),
('Command & Conquer: Red Alert', NULL, '1996-11-22');

INSERT INTO "game_tagging"
(game_id, tag_id, dude_id)
VALUES
(2, 2, 1),
(3, 2, 1),
(4, 2, 1),
(5, 2, 1),
(7, 1, 1),
(7, 2, 1),
(2, 3, 1),
(4, 3, 1),
(5, 3, 1),
(7, 3, 1),
(1, 6, 1),
(8, 4, 1),

(5, 2, 2),
(7, 1, 2),
(7, 2, 2),

(7, 2, 3),
(5, 3, 3),
(7, 3, 3),
(1, 6, 3);
