package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:4200", maxAge =3600)
@RestController
@RequestMapping(path = "/api/movieservice")
public class MovieController {

	
	private MovieService movieService;
	
	public MovieController(final MovieService movieService) {
		// TODO Auto-generated constructor stub
		this.movieService=movieService;
	}
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie,HttpServletRequest request,HttpServletResponse response){
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
    	final String token = authHeader.substring(7);
    	String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
    	System.out.println("userId:"+userId);
    
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() +"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	
	@PutMapping(path ="/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id,@RequestBody final Movie movie){
		ResponseEntity<?> responseEntity;
		
		try {
			final Movie fetchMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchMovie,HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() +"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(path ="/movie/{id}/{userId}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") final String id,@PathVariable("userId") final String userId){
		final Movie mov=movieService.findByIdAndUserId(id, userId);
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(mov.getmId());
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<String>("movie deleted successfully",HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path ="/movie/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id){
		ResponseEntity<?> responseEntity;
		Movie thisMovie = null;
		try {
			thisMovie = movieService.getMovieById(id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Movie>(thisMovie,HttpStatus.OK);
		return responseEntity;
	}
	
	 @GetMapping("/movie")
	 public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest req,final ServletResponse res){
	    final HttpServletRequest request = (HttpServletRequest) req;
	    final String authHeader =  request.getHeader("authorization");
	    final String token = authHeader.substring(7);
	    String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	    System.out.println("userId"+userId);
	    	
	    	return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId),HttpStatus.OK);
	    }

	
	
	
}
