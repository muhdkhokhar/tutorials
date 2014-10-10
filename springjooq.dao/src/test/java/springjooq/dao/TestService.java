package springjooq.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jooq.dao.generated.tables.pojos.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-config.xml" })
public class TestService {

	@Autowired
	private UserMgtService userMgtService;

	@Test
	public void testTxn() {
		Users user = new Users();
		user.setAddressId(1);
		user.setEmail("test@gmail.com");
		user.setPassword("password");
		user.setUsername("testUser");
		userMgtService.inserUser(user);
		

	}

}
