package com.elias.jobtracker.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elias.jobtracker.entities.Curriculum;
import com.elias.jobtracker.services.CurriculumService;

@RestController
@RequestMapping("/curriculums")
public class CurriculumController {

	private final CurriculumService curriculumService;

	public CurriculumController(CurriculumService curriculumService) {
		this.curriculumService = curriculumService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Curriculum> getCurriculum(@PathVariable Long id) {
		Optional<Curriculum> curriculum = curriculumService.findById(id);
		if (curriculum.isPresent()) {
			return ResponseEntity.ok().body(curriculum.get());
		} else {
			throw new RuntimeException("Curriculum not found");
		}
	}

	@GetMapping
	public ResponseEntity<List<Curriculum>> findAll() {
		List<Curriculum> curriculums = curriculumService.findAll();
		return ResponseEntity.ok().body(curriculums);
	}

	@PostMapping("/user/{userId}")
	public ResponseEntity<Curriculum> postCurriculum(@PathVariable Long userId, @RequestBody Curriculum curriculum) {
		Curriculum savedCurriculum = curriculumService.insert(userId, curriculum);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCurriculum.getId())
				.toUri();

		return ResponseEntity.created(uri).body(savedCurriculum);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Curriculum> update(@PathVariable Long id, @RequestBody Curriculum curriculum) {
		curriculum.setId(id);
		Curriculum updatedCurriculum = curriculumService.update(curriculum);
		return ResponseEntity.ok().body(updatedCurriculum);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		curriculumService.delete(id);
		return ResponseEntity.noContent().build();
	}
}