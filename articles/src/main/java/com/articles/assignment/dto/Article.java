package com.articles.assignment.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Article {
	
	private int id;
	private String title;
	private String url;
	private String imageUrl;
	private String newsSite;
	private String summary;
	private LocalDate publishedAt;
	private LocalDate updatedAt;

}
