package ee.eerikmagi.experiments.games_app.api.services.impl;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;
import ee.eerikmagi.experiments.games_app.api.services.ITagService;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.ITagRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
