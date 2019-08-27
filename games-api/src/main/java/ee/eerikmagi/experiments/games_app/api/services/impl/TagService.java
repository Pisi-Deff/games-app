package ee.eerikmagi.experiments.games_app.api.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.ITagRepository;
import ee.eerikmagi.experiments.games_app.api.services.ITagService;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TagService implements ITagService {
	private ITagRepository tagRep;

	@Override
	public Tag getOrCreateTag(String name) {
		Tag t = tagRep.getByNameIgnoreCase(name);
		if (t == null) {
			t = new Tag();
			t.setName(name);
			t = tagRep.save(t);
		}
		return t;
	}

	@Override
	public Slice<Tag> list(Pageable pageable) {
		return tagRep.findAll(pageable);
	}

	@Override
	public Slice<Tag> list(String name, Pageable pageable) {
		return tagRep.findByNameContainingIgnoreCase(name, pageable);
	}
}
