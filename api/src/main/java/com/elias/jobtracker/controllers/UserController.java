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

import com.elias.jobtracker.entities.User;
import com.elias.jobtracker.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			User realUser = user.get();
			return ResponseEntity.ok().body(realUser);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}

	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user) {
		User savedUser = userService.insert(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(uri).body(savedUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		Optional<User> optionalUser = userService.findById(id);

		if (optionalUser.isPresent()) {
			User realUser = optionalUser.get();
			realUser.setEmail(user.getEmail());
			realUser.setName(user.getName());
			userService.update(id, realUser);
			return ResponseEntity.ok().body(realUser);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		Optional<User> user = userService.findById(id);

		if (user.isPresent()) {
			userService.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			throw new RuntimeException("User not found");
		}
	}
}