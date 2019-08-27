package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {
	Tag getByNameIgnoreCase(String name);

	// TODO: figure out way to use postgresql similarity operator instead of "contains"
	// possibly something similar to: http://java-talks.blogspot.com/2014/04/use-postgresql-full-text-search-with-hql.html
	Slice<Tag> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
