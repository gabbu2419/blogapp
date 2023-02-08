package com.myblog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myblog.entities.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repositories.PostRepository;
import com.myblog.services.PostService;


@Service
public class PostServiceImpl implements PostService {


	private ModelMapper modelMapper;
	
	private PostRepository postRepo;
	
	

	public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
		
		this.postRepo = postRepo;
		this.modelMapper=modelMapper;
	}
	
	public Post mapToEntity(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		//all these lines are not needed
//		Post post=new Post();
//		post.setTitle(postDto.getTitle());
//	        post.setDescription(postDto.getDescription());
//	        post.setContent(postDto.getContent());
	        return post;
	}
	
	public PostDto mapToDto(Post postEntity) {
		PostDto postDto = modelMapper.map(postEntity, PostDto.class);
//		PostDto dto=new PostDto();
//		 dto.setId(postEntity.getId());  //as in dto auto increment not used
//		dto.setTitle(postEntity.getTitle());
//        dto.setDescription(postEntity.getDescription());
//        dto.setContent(postEntity.getContent());
        return postDto;
	}



	@Override
	public PostDto createPost(PostDto postDto) {
             Post post = mapToEntity(postDto);       //database can take data from entity not from dto so converting dto to entity		
		     Post postEntity = postRepo.save(post);   
		     PostDto dto = mapToDto(postEntity);      //converting back to dto as this object will carry data b/w processes
		     return dto;
	}

//	@Override
//	public List<PostDto> getAllPosts() {
//		List<Post> posts = postRepo.findAll();
//		//now we want to convert all objects to dto not a single object, we can use stream api
//		List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
//		return dto;
//	}
	//this is for pagination
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();  
		//checking whatever value present in sortDir if it is ascending using ternary operator: can use if else also
		
//		Pageable pageable=PageRequest.of(pageNo, pageSize, Sort.by(sortBy));  //if no sorting order to be defined
		Pageable pageable=PageRequest.of(pageNo, pageSize, sort);
		 Page<Post> posts = postRepo.findAll(pageable);
		 List<Post> content = posts.getContent(); //converts page to list
		
//		List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
//		return contents; and return type will be List<PostDto>
		 //for limits and stuff, POSTRESPONSE CLASS
		 List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		PostResponse postRes=new PostResponse();
		postRes.setContent(contents);
		postRes.setPageNo(posts.getNumber());
		postRes.setPageSize(posts.getSize());
		postRes.setTotalPages(posts.getTotalPages());
		postRes.setTotalElements(posts.getTotalElements());
		postRes.setLast(posts.isLast());
		
		return postRes;
		 
		
		
		 
	}

	@Override
	public PostDto getPostById(long id) {
//		Optional<Post> postById = postRepo.findById(id);
//		Post postEntity = postById.get();
//		PostDto dto = mapToDto(postEntity);
//		return dto;
		
		//NOW WE WILL CHECK IF RECORD IS THERE, IF NOT THERE THEN GO INTO ORELSETHROW()
		
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePostById(PostDto postDto, long id) {
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		Post newPost = postRepo.save(post);
		return mapToDto(newPost);
		
		
	}

	@Override
	public void deletePostById(long id) {
		postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		postRepo.deleteById(id);

	}

	

}
