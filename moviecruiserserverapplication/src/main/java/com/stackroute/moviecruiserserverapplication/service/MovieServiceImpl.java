package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	
	
	private final transient MovieRepository movieRepo;

	@Autowired
	public MovieServiceImpl(final MovieRepository movieRepo) {
		super();
		this.movieRepo=movieRepo;
	}

	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		final Optional<Movie> object = movieRepo.findById(movie.getmId());
		if(object.isPresent()){
			throw new MovieAlreadyExistsException("Could not save movie , movie already exists");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(final Movie updateMovie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie = movieRepo.findById(updateMovie.getmId()).orElse(null);
		if(movie == null) {
			throw new MovieNotFoundException("Couldn't update movie.Movie not found!");
		}
		movie.setComments(updateMovie.getComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(final int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie = movieRepo.findById(id).orElse(null);
		if(movie == null) {
			throw new MovieNotFoundException("Couldn't not delete , Movie not found!");
		}
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(final int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		final Movie movie = movieRepo.findById(id).get();
		if(movie == null) {
			throw new MovieNotFoundException("Movie not found!");
		}
		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		// TODO Auto-generated method stub
		return movieRepo.findByUserId(userId);
	}

	@Override
	public Movie findByIdAndUserId(String id, String userId) {
		// TODO Auto-generated method stub
		return movieRepo.findByIdAndUserId(id, userId);
	}

}
