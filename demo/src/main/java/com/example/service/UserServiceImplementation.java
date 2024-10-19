package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repo.UserRepo;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	UserRepo userRepo;
	
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setId(user.getId());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		
		User savedUser = userRepo.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserByID(Integer userId)throws Exception {
		List<User> users = new ArrayList<>();
		
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new Exception("id miss-match: id is not present");
		}
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2)throws Exception {
		// TODO Auto-generated method stub
		User user1 = findUserByID(userId1);
		User user2 = findUserByID(userId2);
		
		user2.getFollowrs().add(user1.getId());
		user1.getFollwings().add(user2.getId());
		
		userRepo.save(user1);
		userRepo.save(user2);
		return user1;
		
	}

	@Override
	public User updateUser(User user,Integer userId)throws Exception {
Optional<User> user1= userRepo.findById(userId);
		
		
		if(user1.isEmpty()) {
			throw new Exception("user not exists");
		}
		
		User olduser = user1.get();
		
		if(user.getFirstName()!=null) {
			olduser.setFirstName(user.getFirstName());
		}
		if(user.getLastName() != null) {
			olduser.setLastName(user.getLastName());
		}
		if(user.getId()!= null) {
			olduser.setId(user.getId());
		}
		if(user.getEmail()!=null) {
			olduser.setEmail(user.getEmail());
		}
		if(user.getPassword()!=null) {
			olduser.setPassword(user.getPassword());
		}
		User updatedUser = userRepo.save(olduser);
		
		return updatedUser;
	}

	@Override
	
	public String delteUserById(Integer userId)throws Exception {
		
		Optional<User> user1= userRepo.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("user not exists");
		}
		userRepo.delete(user1.get());
		
		return "user deleted succesfully with id: "+userId;
	}
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		return userRepo.searchUser(query);
	}

}
