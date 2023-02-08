package com.myblog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    
    public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	private String body;
    
    private String email;
    
    private String name;
    
  //lazy for better performance
    @ManyToOne(fetch = FetchType.LAZY)     //this comment class is mapped to Post in ManyToOne form
    //join column means how do u define foreign key
    @JoinColumn(name="post_id", nullable=false)
    private Post post;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
    
	
}
