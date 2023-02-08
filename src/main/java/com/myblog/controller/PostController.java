package com.myblog.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.services.PostService;
import com.myblog.util.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//we will not use void method, here we create RsponseEntity type
	//public void createPost() {}  -> void method cannot return back the response code
    @PostMapping
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
   if(bindingResult.hasErrors()){
       return  new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
        return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    
	}
    
//    @GetMapping
//    public List<PostDto> getAllPost(){
//    	List<PostDto> postDto = postService.getAllPosts();
//      	return postDto;
// 
//    }
    
    //this is for pagination and sorting
//    localhost:8080/api/posts?pageSize=10&sortBy=title
    @GetMapping
//    public PostResponse getAllPost(@RequestParam(value="pageNo", defaultValue = "0", required=false) int pageNo,
    public PostResponse getAllPost(@RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required=false) int pageNo,		
    @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required=false) int pageSize,
               @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
               @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false)String sortDir){
//    	List<PostDto> postDto = postService.getAllPosts(pageNo, pageSize);
//      	return postDto; and type will be List<PostDto>
    	
    	//for postResponse
//    	return postService.getAllPosts(pageNo, pageSize);
    	
    	
    	return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
    	PostDto postDtoById = postService.getPostById(id);
//    	return new ResponseEntity<>(postDtoById, HttpStatus.OK);
    	return ResponseEntity.ok(postDtoById);  //both are correct
    	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name="id") long id){
         PostDto updatePostById = postService.updatePostById(postDto,id);	
         return ResponseEntity.ok(updatePostById);
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
    	postService.deletePostById(id);
    	return ResponseEntity.ok("Post Deleted for id: "+id); 
    	
    }

}
