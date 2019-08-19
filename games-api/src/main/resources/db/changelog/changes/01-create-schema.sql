CREATE TABLE "dude"
(
    id BIGSERIAL PRIMARY KEY,
    uuid UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
    email VARCHAR(200) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    display_name VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX "ux_dude_email" ON "dude" (UPPER(email));

CREATE TABLE "game"
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(300) NOT NULL,
    description TEXT,
    release_date DATE NOT NULL
);

CREATE TABLE "game_review"
(
    id BIGSERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL REFERENCES "game" (id) ON DELETE CASCADE,
    dude_id BIGINT NOT NULL REFERENCES "dude" (id) ON DELETE CASCADE,
    score INT NOT NULL,
    review TEXT,
    review_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
    UNIQUE (game_id, dude_id)
);
CREATE INDEX "i_game_review_fk_dude" ON "game_review" (dude_id);
CREATE INDEX "i_game_review_fk_game" ON "game_review" (game_id);

CREATE TABLE "tag"
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX "ux_tag_name" ON "tag" (UPPER(name));

CREATE TABLE "game_tagging"
(
    id BIGSERIAL PRIMARY KEY,
    game_id BIGINT NOT NULL REFERENCES "game" (id) ON DELETE CASCADE,
    tag_id BIGINT NOT NULL REFERENCES "tag" (id) ON DELETE CASCADE,
    dude_id BIGINT NOT NULL REFERENCES "dude" (id) ON DELETE CASCADE,
    UNIQUE (game_id, tag_id, dude_id)
);
CREATE INDEX "i_game_tagging_fk_dude" ON "game_tagging" (dude_id);
CREATE INDEX "i_game_tagging_fk_game" ON "game_tagging" (game_id);
CREATE INDEX "i_game_tagging_fk_tag" ON "game_tagging" (tag_id);
