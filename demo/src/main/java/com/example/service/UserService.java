

package com.example.service;
import java.util.List;

import com.example.models.*; 
public interface UserService {

		public User registerUser(User user);
		
		public User findUserByID(Integer userId)throws Exception;
		
		public User findUserByEmail(String email);
		
		public User followUser(Integer userId1, Integer userId2)throws Exception;
		
		public User updateUser(User user,Integer userId)throws Exception;
		
		public List<User> searchUser(String query);

		public String delteUserById(Integer userId)throws Exception;
		
		
		
}
