package com.myblog.entities;



import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="posts")


public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title",nullable=false, unique = true)
	private String title;
	
	@Column(name="description", nullable=false)
	private String description;
	
	
	@Lob
	@Column(name="content", nullable=false)
	private String content;
	
	// cascade  means- any changes made in parent should reflect in child i.e if we delete blog, all comments also should be deleted
	//orphanRemoval - to automatically remove any data if it is orphan or doesn't belong to anyone
	@OneToMany(mappedBy = "post", cascade =CascadeType.ALL, orphanRemoval = true )  //bi directional mapping- i.e many to one and one to many
	Set<Comment> comments=new HashSet<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	


		


}
