package com.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String caption;
	private String image;
	private String video;
	
	@ManyToOne
	private User user;
	private LocalDateTime createdAt;
	
	@OneToMany
	private List<User> liked = new ArrayList<>();
	
	
	
	public Post(Integer id, String caption, String imgage, String video, User user, LocalDateTime createdAt,
			List<User> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = imgage;
		this.video = video;
		this.user = user;
		this.createdAt = createdAt;
		this.liked = liked;
	}
	
	
	public List<User> getLiked() {
		return liked;
	}
	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String imgage) {
		this.image = imgage;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Post(){
		
	}
	
}
