package com.myblog.payload;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDto {
	
	private long id;



	@NotNull
	@Size(min=10, message="Post Description should have at least 10 characters or more")
	private String description;
	@NotNull
	@Size(min=2, message="Post Title should have at least 2 characters")
	private String title;
	@NotNull
	@NotEmpty
	private String content;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
