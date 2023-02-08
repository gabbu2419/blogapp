package com.myblog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPostId(long postId);

}
