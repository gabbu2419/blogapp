package com.myblog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.entities.Comment;
import com.myblog.entities.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repositories.CommentRepository;
import com.myblog.repositories.PostRepository;
import com.myblog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired  // using postRepo here because saving comment based on postId
	private PostRepository postRepo;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {  //for this postId, save the comment. here no simple saving of comment
		 Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));  //for this post we have to save the comments
      		
		 Comment comment = mapToComment(commentDto);
		 comment.setPost(post);
		 Comment newComment = commentRepo.save(comment);
		
		CommentDto dto = mapToDto(newComment);
		return dto;
	}
	
 Comment mapToComment(CommentDto commentDto) {
	 Comment comment = modelMapper.map(commentDto, Comment.class);

	 return comment;
 }
 
 CommentDto mapToDto(Comment comment) {
	 CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

	 return commentDto;
 }

@Override
public List<CommentDto> getCommentByPostId(long postId) {
	List<Comment> comments = commentRepo.findByPostId(postId);  //was not there firstly, custom finder method
	List<CommentDto> dto = comments.stream().map(c-> mapToDto(c)).collect(Collectors.toList());
	return dto;
}

@Override
public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
	Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId)); //to check if post exist
	Comment comment = commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("comment", "id", id));
	
	comment.setName(commentDto.getName());
	comment.setEmail(commentDto.getEmail());
	comment.setBody(commentDto.getBody());
	Comment updatedComment = commentRepo.save(comment);
	CommentDto dto = mapToDto(updatedComment);
	return dto;
}

@Override
public void deleteComment(long postId, long commentId) {
	Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId)); 
	Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
	commentRepo.deleteById(commentId);
	
	
}

}
