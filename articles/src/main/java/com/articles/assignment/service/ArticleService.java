package com.articles.assignment.service;

import java.util.List;

import com.articles.assignment.dto.Article;

public interface ArticleService {
		
		List<Article> getAtrticalesBetweenDates(String fromDate,String toDate);

}
