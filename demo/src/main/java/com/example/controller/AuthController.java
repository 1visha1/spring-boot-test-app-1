package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.jwtProvider;
import com.example.models.User;
import com.example.repo.UserRepo;
import com.example.service.UserService;

@RequestMapping
@RestController("/auth")
public class AuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user)throws Exception {
		
		User isExist = userRepo.findByEmail(user.getEmail());
		if(isExist != null) {
			throw new Exception("Email already use by another account");
		}
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userService.registerUser(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token,"Register sucess");
		return res;
			
		
		
	}
}
