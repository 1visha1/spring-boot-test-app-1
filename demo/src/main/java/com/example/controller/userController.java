package com.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.example.models.User;
import com.example.repo.UserRepo;
import com.example.service.UserService;

@RestController
public class userController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers(){
		List<User> users = userRepo.findAll();
		return users;
	}
	
	@GetMapping("/api/users/{useId}")
	public User getUserById(@PathVariable("useId") Integer id)  throws Exception{
		return userService.findUserByID(id);

	}
	
	
	
	@PutMapping("/api/users/{userId}")
	public User updateUser(@PathVariable Integer userId,@RequestBody User user)throws Exception {
		
		return userService.updateUser(user, userId);
		
	}
	
	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2)throws Exception {
	
		return userService.followUser(userId1, userId2);
	}
	
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@DeleteMapping("/api/users/{userId}")
	public String deleteUser(@PathVariable Integer userId)throws Exception {
		
		return userService.delteUserById(userId);
	}
	
	
}
