package com.devsuperior.hrworker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.exceptions.ObjectNotFoundException;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repo;
	
	public List<Worker> findAll(){
		return repo.findAll();
	}
	
	public Worker findById(Long id) {
		Optional<Worker> worker=repo.findById(id);
		return worker.orElseThrow(()->new ObjectNotFoundException("object not found"));
	}
	
}
