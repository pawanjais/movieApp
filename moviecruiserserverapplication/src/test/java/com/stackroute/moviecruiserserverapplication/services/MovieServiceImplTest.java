package com.stackroute.moviecruiserserverapplication.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;
import com.stackroute.moviecruiserserverapplication.service.MovieServiceImpl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;


public class MovieServiceImplTest {

	public MovieServiceImplTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Mock
	private transient MovieRepository movieRepo;
	
	private transient Movie movie;
	
	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;

	
	transient Optional<Movie> options;
	
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145");
		options=Optional.of(movie);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull("jpaRepository creation fails : use @injectMocks on movieServiceImpl",movie);
	}
	
	
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("Saving movie failed",flag);
		verify(movieRepo,times(1)).save(movie);
		verify(movieRepo,times(1)).findById(movie.getmId());

	}
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		System.out.println(flag);
		assertFalse("Saving movie failed",flag);
		verify(movieRepo,times(1)).findById(movie.getmId());


	}
	
	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("not so good movie");
		final Movie movie1 = movieServiceImpl.updateMovie(movie);
		assertEquals("updating movie failed","not so good movie",movie1.getComments());
		verify(movieRepo,times(1)).save(movie);
		verify(movieRepo,times(1)).findById(movie.getmId());

	}
	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		final boolean flag = movieServiceImpl.deleteMovieById(1);
		assertTrue("deleting movie failed",flag);
		verify(movieRepo,times(1)).delete(movie);
		verify(movieRepo,times(1)).findById(movie.getmId());
	}
	
	@Test
	public void testGetMovieById() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		final Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("fetching movie by id failed",movie1,movie);
		verify(movieRepo,times(1)).findById(movie.getmId());
	}
	
	@Test
	public void testGetAllMovies(){
		final List<Movie> movieList= new ArrayList<>(1);
		when(movieRepo.findAll()).thenReturn(movieList);
		final List<Movie> movie1=movieServiceImpl.getMyMovies("john145");
		assertEquals(movieList,movie1);
		verify(movieRepo,times(1)).findByUserId("john145");
	}

}
