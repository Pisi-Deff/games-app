package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;

public interface ITagService {
	Tag getOrCreateTag(String name);
}
