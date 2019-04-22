package com.stackroute.moviecruiserauthenticationservice.repository;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserauthenticationservice.domain.User;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	public UserRepositoryTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserRepository userRepository;
	
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User("rahul","gogyani","abc","123456789", new Date());
	}
	
	@Test
	public void testRegisterSuccess() {
		userRepository.save(user);
		Optional<User> option =userRepository.findById(user.getUserId());
		assertThat(option.equals(user));
		}


}
