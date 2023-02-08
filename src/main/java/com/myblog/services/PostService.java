package com.myblog.services;

import java.util.List;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

public interface PostService {
	//earlier we were giving directly entity class name i.e. Lead lead
	PostDto createPost(PostDto postDto);

//	List<PostDto> getAllPosts();
//	List<PostDto> getAllPosts(int pageNo, int pageSize);
	
	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

	

	PostDto getPostById(long id);

	PostDto updatePostById(PostDto postDto, long id);

	void deletePostById(long id);

}
