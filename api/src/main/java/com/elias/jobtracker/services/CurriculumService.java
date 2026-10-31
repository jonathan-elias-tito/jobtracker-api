package com.elias.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elias.jobtracker.entities.Curriculum;
import com.elias.jobtracker.entities.User;
import com.elias.jobtracker.interfaces.ResumeRepository;
import com.elias.jobtracker.interfaces.UserRepository;

@Service
public class CurriculumService {

	private final ResumeRepository resumeRepository;
	private final UserRepository userRepository;

	public CurriculumService(ResumeRepository resumeRepository, UserRepository userRepository) {
		this.resumeRepository = resumeRepository;
		this.userRepository = userRepository;
	}

	public Curriculum insert(Long userId, Curriculum curriculum) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			curriculum.setUser(user);
		} else {
			throw new RuntimeException("User not found with id: " + userId);
		}
		return resumeRepository.save(curriculum);
	}

	public Optional<Curriculum> findById(Long id) {
		return resumeRepository.findById(id);
	}

	public List<Curriculum> findAll() {
		return resumeRepository.findAll();
	}

	public void delete(Long id) {
		if (!resumeRepository.existsById(id)) {
			throw new RuntimeException("Curriculum not found with id: " + id);
		}
		resumeRepository.deleteById(id);
	}
}