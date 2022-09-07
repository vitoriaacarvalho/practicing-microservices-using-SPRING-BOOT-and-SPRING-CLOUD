package com.hruser.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hruser.entities.User;
import com.hruser.repositories.UserRepository;
import com.hruser.services.exceptions.ResourceNotFoundException;


@Service
public class UserServices {
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){;
		return repo.findAll();
	}
	
	public User findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Optional<User> findById(Long id) {
		Optional<User> user=repo.findById(id);
		return user;
	}
	
	public User insert(User user) {
		return repo.save(user);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public User update(Long id, User user) {
		try {
			User entity=repo.getReferenceById(id);
			updateData(entity, user);
			return repo.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}


	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
	}
	
	
	
	
	
	
	
}
