package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	public User findByEmail(String Email);
	
	@Query("select u from User u where u.firstName LIKE %:query%  OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
}
