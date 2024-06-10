package com.articles.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.articles.assignment.dto.Article;
import com.articles.assignment.service.ArticleService;

@RestController
@RequestMapping("/article/")
public class NewsController {
	
	@Autowired
	private ArticleService service;
	
	
	@GetMapping(value = "/news", produces = "application/json")
	
	public List<Article> getNews(@RequestParam("from") String fromDate,@RequestParam("to") String toDate ) {
		
		List<Article> articleNews = service.getAtrticalesBetweenDates(fromDate, toDate);
		
		return articleNews;
		
	}
	
	

}
