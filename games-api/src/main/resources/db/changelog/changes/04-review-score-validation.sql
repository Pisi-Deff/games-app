ALTER TABLE "game_review"
    ADD CONSTRAINT c_game_review_score_limit CHECK (score >= 1 AND score <= 5);
