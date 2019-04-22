package com.stackroute.moviecruiserserverapplication.repositories;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepoTest {

	public MovieRepoTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private transient MovieRepository repo;
	
	
	public void setRepo(final MovieRepository repo){
		this.repo=repo;
	}
	
	@Test
	public void testSaveMovie() throws Exception {
		repo.save(new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145"));
		final Movie getMovie = repo.getOne(1);
		assertThat(getMovie.getmId()).isEqualTo(1);

	}
	
	@Test
	public void testUpdateMovie() throws Exception {
		Movie savedMovie=repo.save(new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145"));
		final Movie movie = repo.getOne(savedMovie.getmId());
		assertEquals(movie.getTitle(),"superman");
		movie.setComments("hi");
		repo.save(movie);
		final Movie tempMovie = repo.getOne(savedMovie.getmId());
		assertEquals("hi",tempMovie.getComments());
		
	}
	
	@Test
	public void testDeleteMovie() throws Exception {
		Movie savedMovie=repo.save(new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145"));
		final Movie movie = repo.getOne(savedMovie.getmId());
		assertEquals(movie.getTitle(),"superman");
		repo.delete(movie);
		assertEquals(Optional.empty(),repo.findById(savedMovie.getmId()));
		
	}
	
	@Test
	public void testGetMovie() throws Exception {
		Movie savedMovie=repo.save(new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145"));
		final Movie movie = repo.getOne(savedMovie.getmId());
		assertEquals(movie.getTitle(),"superman");
		
	}
	
	@Test
	public void testGetAllMovie() throws Exception {
		repo.save(new Movie(1,"1234","superman","good movie","www.abc.com","25-03-23",45.5,112,"john145"));
		repo.save(new Movie(2,"1234","Batman","good movie","www.abc.com","25-03-23",45.5,112,"john4145"));
		final List<Movie> movies = repo.findAll();
		assertEquals(movies.get(0).getTitle(),"superman");
		
	}
	
	@After
    public void tearDown() {
        repo.deleteAllInBatch();;
    }

}
