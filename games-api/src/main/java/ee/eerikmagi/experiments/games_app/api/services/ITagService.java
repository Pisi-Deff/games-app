package ee.eerikmagi.experiments.games_app.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;

public interface ITagService {
	Tag getOrCreateTag(String name);
	Page<String> list(Pageable pageable);
	Slice<String> list(String name, Pageable pageable);
}
