package com.elias.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elias.jobtracker.entities.User;
import com.elias.jobtracker.interfaces.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User insert(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			throw new RuntimeException("Email já cadastrado");
		}
		return userRepository.save(user);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> update(Long id, User obj) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User entity = optionalUser.get();
			entity.setName(obj.getName());
			entity.setEmail(obj.getEmail());
			return Optional.of(userRepository.save(entity));
		}
		return Optional.empty();
	}

	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User not found with id: " + id);
		}
		userRepository.deleteById(id);
	}
}