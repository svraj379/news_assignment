package com.articles.assignment.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.articles.assignment.client.GetAllNewsArticlesClient;
import com.articles.assignment.dto.Article;
import com.articles.assignment.exception.DatesMismatchException;
import com.articles.assignment.exception.InvalidDateFormatException;
import com.articles.assignment.exception.NoRecordsFoundException;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	GetAllNewsArticlesClient articleClient;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	@Override
	public List<Article> getAtrticalesBetweenDates(String fromDate, String toDate) {

		if(!validateDate(fromDate) || !validateDate(toDate)) {
			throw new InvalidDateFormatException();
		}

		LocalDate from = LocalDate.parse(fromDate, formatter);
		LocalDate to = LocalDate.parse(toDate, formatter);

		if(!compareFromAndToDates(from, to)) {
			throw new DatesMismatchException();
		}

		Predicate<LocalDate> compDates = p -> {

			if(p.isEqual(from) || p.isEqual(to)) {
				return true;
			}
			if(p.isAfter(from) && p.isBefore(to)) {
				return true;
			}
			return false;
		};


		List<Article> articleNews = articleClient.getArticleNews();

		List<Article> filteredNews = articleNews.stream()
				.filter( s1 -> compDates.test(s1.getPublishedAt()))
				.collect(Collectors.toList());

		if(ObjectUtils.isEmpty(filteredNews)) {
			throw new NoRecordsFoundException();
		}

		return filteredNews;
	}


	private boolean validateDate(String aDate) {

		try {
			LocalDate.parse(aDate, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean compareFromAndToDates(LocalDate from,LocalDate to) {

		if(from.isEqual(to)) {
			return true;
		}

		if(from.isBefore(to)) {
			return true;
		}
		return false;
	}

}
