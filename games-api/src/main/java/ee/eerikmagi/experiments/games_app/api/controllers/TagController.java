package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.TagDTO;
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
	public Slice<TagDTO> get(@RequestParam(required = false) String name, Pageable pageable) {
		return null;
	}
}
