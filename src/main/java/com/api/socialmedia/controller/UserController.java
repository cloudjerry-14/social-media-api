
package com.api.socialmedia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.socialmedia.entity.UserEntity;
import com.api.socialmedia.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/v1/Users")
	public List<UserEntity> getUsers(){	
		return  userService.getAllUsers();
	}
	
	@GetMapping("/v1/Users/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping("/v1/Users")
	public ResponseEntity<UserEntity> addUser( @Valid @RequestBody UserEntity user){
		UserEntity savedUser=userService.saveUser(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();	
	}
	
	@DeleteMapping("/v1/Users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return ResponseEntity.ok("The user with id : "+ id + " is deleted");
	}
}

