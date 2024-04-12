package com.api.security_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.security_api.entity.User;
import com.api.security_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/welcome")
	public String welcomePage() {
		return "Welcome to my WebPage";
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(service.getUsers());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		return ResponseEntity.ok(service.getUser(id));
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@PutMapping("/update/{id}")
//	@PreAuthorize("/hasAuthority('USER')")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
		return ResponseEntity.ok(service.updateUser(user, id));
	}
	
	@DeleteMapping("/deleteall")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public void removeUsers() {
		service.removeUsers();
	}
	
	@DeleteMapping("/delete/{id}")
//	@PreAuthorize("hasAuthority('USER')")
	public void removeById(@PathVariable Long id) {
		service.removeUserById(id);
	}
	
	
}
