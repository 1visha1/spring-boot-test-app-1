package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.models.Post;
import com.example.response.ApiResponse;
import com.example.service.PostImplemntation;
@RestController
public class PostController {
	
	@Autowired
	PostImplemntation postService;
	
	@PostMapping("/api/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception{
		
		Post createdPost = postService.createNewPost(post, userId);
		
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteExchange("/api/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost( @PathVariable Integer postId, @PathVariable Integer userId)throws Exception{
		
		String message = postService.deletePost(postId, userId);
		ApiResponse res = new ApiResponse("message", true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId )throws Exception{
		
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/api/posts/save/{postId}/user/{userId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId,@PathVariable Integer userId )throws Exception{
		Post post = postService.savedPost(postId, userId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId )throws Exception{
		Post post = postService.likePost(postId, userId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
}
