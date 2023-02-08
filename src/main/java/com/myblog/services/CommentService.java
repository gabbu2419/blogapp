package com.myblog.services;

import java.util.List;

import com.myblog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(long postId, CommentDto commentDto);
	List<CommentDto> getCommentByPostId(long postId);
	CommentDto updateComment(long postId, long id, CommentDto commentDto);
	void deleteComment(long postId, long commentId);

}
