package com.myblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myblog.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
