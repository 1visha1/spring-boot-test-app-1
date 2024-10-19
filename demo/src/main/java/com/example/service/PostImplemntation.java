package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Post;
import com.example.models.User;
import com.example.repo.*;

@Service
public class PostImplemntation implements PostService {
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;
	

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		
		User user = userService.findUserByID(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setId(post.getId());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return postRepo.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserByID(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("you can't delete this post");
		}
		postRepo.delete(post);
		
		return "post is deleted";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		return postRepo.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt = postRepo.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("post not found with id: "+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepo.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId)throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserByID(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		
		return postRepo.save(post);
	}

	@Override
	public Post likePost(Integer postId, Integer userId)throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserByID(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		return postRepo.save(post);
	}

}
