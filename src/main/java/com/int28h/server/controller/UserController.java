package com.int28h.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.int28h.server.exception.ResourceNotFoundException;
import com.int28h.server.model.User;
import com.int28h.server.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/users")
	public Integer addUser(@Valid @RequestBody User user) {
		return userRepository.save(user).getId();
	}
	
	@GetMapping("/users/{id}")
    public User getNoteById(@PathVariable(value = "id") int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
	
	@PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") int id,
                                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setTitle(userDetails.getTitle());
        user.setContent(userDetails.getContent());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
}
