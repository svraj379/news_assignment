package com.articles.assignment.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.articles.assignment.dto.Article;

@Component
public class GetAllNewsArticlesClient {
	
	@Value("${news.api.url}")
	private String articaleURL;
	
	@Autowired
	private RestTemplate restTemplate;

	
	public List<Article> getArticleNews() {
	
	try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<List<Article>> response = restTemplate.exchange(articaleURL, HttpMethod.GET,entity,new ParameterizedTypeReference<List<Article>>() {});
		
		return response.getBody();
	} catch (RestClientException e) {
		throw e;
	} catch (Exception e) {
		throw e;
	}
	
	}
	
}
