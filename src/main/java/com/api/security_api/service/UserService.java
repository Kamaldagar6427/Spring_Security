package com.api.security_api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.security_api.entity.User;
import com.api.security_api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getUsers() {
		return repository.findAll();
	}

	public User getUser(Long id) {
		return repository.findById(id).get();
	}

	public String addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		return "User added to the system";
	}

	public User updateUser(User user, Long id) {
		User u = repository.findUserById(id);
		if (u != null) {
			u.setName(user.getName());
    		u.setAddress(user.getAddress()); 
    		u.setEmail(user.getEmail());
    		u.setPassword(passwordEncoder.encode(user.getPassword()));
    		u.setRoles(user.getRoles());;
    		return repository.save(u);
		}
		return null;
	}

	public void removeUsers() {
		repository.deleteAll();
		
	}

	public void removeUserById(Long id) {
		repository.deleteById(id);
		
	}

	

	
	
}
