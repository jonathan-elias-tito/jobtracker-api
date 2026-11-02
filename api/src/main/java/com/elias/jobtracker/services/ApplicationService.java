package com.elias.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elias.jobtracker.entities.Application;
import com.elias.jobtracker.entities.Curriculum;
import com.elias.jobtracker.interfaces.ApplicationsRepository;
import com.elias.jobtracker.interfaces.ResumeRepository;

@Service
public class ApplicationService {

	private final ResumeRepository resumeRepository;
	private final ApplicationsRepository applicationRepository;

	public ApplicationService(ApplicationsRepository applicationRepository, ResumeRepository resumeRepository) {
		this.applicationRepository = applicationRepository;
		this.resumeRepository = resumeRepository;
	}

	public Application insert(Long curriculumId, Application application) {
		Optional<Curriculum> initialCurriculum = resumeRepository.findById(curriculumId);

		if (initialCurriculum.isPresent()) {
			Curriculum realCurriculum = initialCurriculum.get();
			application.setCurriculum(realCurriculum);
			return applicationRepository.save(application);
		} else {
			throw new RuntimeException("Curriculum not found");
		}
	}

	public Optional<Application> findById(Long id) {
		return applicationRepository.findById(id);
	}

	public List<Application> findAll() {
		return applicationRepository.findAll();
	}

	public void delete(Long id) {
		if (!applicationRepository.existsById(id)) {
			throw new RuntimeException("Application not found with id: " + id);
		}
		applicationRepository.deleteById(id);
	}

	public Application update(Application application) {
		Optional<Application> app = applicationRepository.findById(application.getId());
		if (app.isPresent()) {
			Application realApp = app.get();
			realApp.setApplicationDate(application.getApplicationDate());
			realApp.setCurriculum(application.getCurriculum());
			realApp.setStatus(application.getStatus());
			return applicationRepository.save(realApp);
		} else {
			throw new RuntimeException("Application not found");
		}
	}
}