package com.example.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Integer> followrs = new ArrayList<>();
	private List<Integer> follwings = new ArrayList<>();
	private String gender;
	
	@ManyToMany
	private List<Post> savedPost = new ArrayList<>();
	
	 
	public List<Integer> getFollowrs() {
		return followrs;
	}
	public void setFollowrs(List<Integer> followrs) {
		this.followrs = followrs;
	}
	public List<Integer> getFollwings() {
		return follwings;
	}
	public void setFollwings(List<Integer> follwings) {
		this.follwings = follwings;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public List<Post> getSavedPost() {
		return savedPost;
	}
	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}
	public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followrs,
			List<Integer> follwings, String gender, List<Post> savedPost) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.followrs = followrs;
		this.follwings = follwings;
		this.gender = gender;
		this.savedPost = savedPost;
	}
	public User() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
