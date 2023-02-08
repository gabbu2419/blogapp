package com.myblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.myblog.payload.CommentDto;
import com.myblog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
		CommentDto dto = commentService.createComment(postId, commentDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping("posts/{postId}/comments") //localhost:8080/api/posts/1/comments
	public List<CommentDto> getAllCommentsByPostId(@PathVariable("postId") long postId){
		List<CommentDto> dto = commentService.getCommentByPostId(postId);
		return dto;
		
	}
	
	@PutMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId, @PathVariable("id") long id,
			@RequestBody CommentDto commentDto){
		CommentDto dto = commentService.updateComment(postId, id, commentDto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
			@PathVariable("id") long commentId){
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment is deleted", HttpStatus.OK);
	}

}
