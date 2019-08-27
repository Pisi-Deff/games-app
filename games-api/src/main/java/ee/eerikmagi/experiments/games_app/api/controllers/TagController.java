package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.TagDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;
import ee.eerikmagi.experiments.games_app.api.services.ITagService;

@RestController
@RequestMapping("tags")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TagController {
	private ModelMapper modelMapper;
	private ITagService tagSvc;

	@GetMapping
	@ResponseBody
	public Slice<TagDTO> get(
		@RequestParam(required = false) String name,
		Pageable pageable
	) {
		// there's currently no way to specify `ignoreCase()` over annotations nor via request
		// can't think of use case where frontend wouldn't want sort by name, so let's just always override
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
			Sort.by(Sort.Order.asc("name").ignoreCase()));

		Slice<Tag> tags;
		if (StringUtils.isBlank(name)) {
			tags = tagSvc.list(pageable);
		} else {
			tags = tagSvc.list(name, pageable);
		}

		return tags.map(t -> modelMapper.map(t, TagDTO.class));
	}
}
