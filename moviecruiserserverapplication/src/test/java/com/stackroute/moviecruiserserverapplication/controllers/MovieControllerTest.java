package com.stackroute.moviecruiserserverapplication.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.moviecruiserserverapplication.controller.MovieController;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)

public class MovieControllerTest {

	public MovieControllerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient MovieService service;
	
	private transient Movie movie;
	
	static List<Movie> movies;
	
	@Before
	public void setup() {
		movies=new ArrayList<>();
		movie=new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"101");
		movies.add(movie);
		movie=new Movie(2,"12547","POC-2","very good movie","www.abc.com","2013-09-23",45.5,112,"101");
		movies.add(movie);
		movie=new Movie(3,"12557","POC-3","very good movie","www.abc.com","2013-09-23",45.5,112,"101");
		movies.add(movie);
		
	}

	@Test
	public void testSaveMovieSuccess() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movieservice/movie").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(service,times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
		
	}
	
	@Test
	public void testUpdateMovieSuccess() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		movie.setComments("not so good");
		when(service.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/movieservice/movie/{id}",1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
		.andExpect(status().isOk());
		verify(service,times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}
	
/*	@Test
	public void testDeleteMovieById()throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/movieservice/movie/{id}/{userId}","1234","101").header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(service,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}
*/

	
	@Test
	public void testFetchMovieById()throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		when(service.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/movieservice/movie/{id}",1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(service,times(1)).getMovieById(1);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testGetMyMovies() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		when(service.getMyMovies("101")).thenReturn(movies);
		mvc.perform(get("/api/movieservice/movie")
				.header("authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());
		verify(service,times(1)).getMyMovies("101");
		verifyNoMoreInteractions(service);
	}

	
/*	@Test
	public void testFetchAllMovies() throws Exception {
		when(service.getAllMovies()).thenReturn(null);
		mvc.perform(get("/api/movie")).andExpect(status().isOk());
		verify(service,times(1)).getAllMovies();
		verifyNoMoreInteractions(service);
	}*/
	private static String jsonToString(final Object obj) throws JsonProcessingException{
		String result;
		try {
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result =jsonContent;
					
		}catch(JsonProcessingException e) {
			result="json processing error";
		}
		return result;
	}



}
