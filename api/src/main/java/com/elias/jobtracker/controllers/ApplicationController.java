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

import com.elias.jobtracker.entities.Application;
import com.elias.jobtracker.services.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

	private final ApplicationService applicationService;

	public ApplicationController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Application> getApplication(@PathVariable Long id) {
		Optional<Application> application = applicationService.findById(id);
		if (application.isPresent()) {
			return ResponseEntity.ok().body(application.get());
		} else {
			throw new RuntimeException("Application not found");
		}
	}

	@GetMapping
	public ResponseEntity<List<Application>> findAll() {
		List<Application> applications = applicationService.findAll();
		return ResponseEntity.ok().body(applications);
	}

	@PostMapping("/curriculum/{curriculumId}")
	public ResponseEntity<Application> insert(@PathVariable Long curriculumId,
			@RequestBody Application application) {

		Application savedApplication = applicationService.insert(curriculumId, application);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedApplication.getId()).toUri();

		return ResponseEntity.created(uri).body(savedApplication);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Application> update(@PathVariable Long id, @RequestBody Application application) {
		application.setId(id);
		Application updatedApplication = applicationService.update(application);
		return ResponseEntity.ok().body(updatedApplication);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		applicationService.delete(id);
		return ResponseEntity.noContent().build();
	}
}