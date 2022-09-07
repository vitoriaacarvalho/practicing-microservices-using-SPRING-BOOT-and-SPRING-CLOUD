package com.hruser.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hruser.entities.User;
import com.hruser.services.UserServices;

@RequestMapping(value="/users")
@RestController
public class UserResource {
	@Autowired
	private UserServices service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users=service.findAll();
		return ResponseEntity.ok().body(users);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
		Optional<User> user=service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	@GetMapping(value="/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		User user=service.findByEmail(email);
		return ResponseEntity.ok().body(user);
	}
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		user=service.insert(user);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user ){
		user=service.update(id,user);
		return ResponseEntity.ok().body(user);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
