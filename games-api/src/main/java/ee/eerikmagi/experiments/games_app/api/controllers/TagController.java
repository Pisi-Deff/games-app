package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.services.ITagService;

@RestController
@RequestMapping("tags")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TagController {
	private ITagService tagSvc;

	@GetMapping(params = "name")
	@ResponseBody
	public Slice<String> search(
		@RequestParam(required = false) String name,

		@PageableDefault
		Pageable pageable
	) {
		return tagSvc.list(name, makePageable(pageable));
	}

	@GetMapping
	@ResponseBody
	public Page<String> get(@PageableDefault Pageable pageable) {
		return tagSvc.list(makePageable(pageable));
	}

	private PageRequest makePageable(@PageableDefault Pageable pageable) {
		// there's currently no way to specify `ignoreCase()` over annotations nor via request
		// can't think of use case where frontend wouldn't want sort by name, so let's just always override
		return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
			Sort.by(Sort.Order.asc("name").ignoreCase()));
	}
}
