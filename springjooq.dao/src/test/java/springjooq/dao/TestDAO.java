package springjooq.dao;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jooq.dao.generated.tables.daos.AddressDao;
import spring.jooq.dao.generated.tables.daos.UsersDao;
import spring.jooq.dao.generated.tables.pojos.Address;
import spring.jooq.dao.generated.tables.pojos.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-config.xml" })
public class TestDAO {

	@Autowired
	private DSLContext cxt;

	@Test
	public void testFetch() {
		Assert.assertNotNull(cxt);

		AddressDao addressDao = new AddressDao(cxt.configuration());
		UsersDao usersDao = new UsersDao(cxt.configuration());

		List<Address> addressesByID = addressDao.fetchById(1);

		Assert.assertEquals(1, addressesByID.size());

		 Users users = new Users();
		 users.setAddressId(addressesByID.get(0).getId());
		 users.setEmail("Test@gmail.com");
		 users.setPassword("pass");
		 users.setUsername("testUser");
		 usersDao.insert(users);

//		Users fetchOneByUsername = usersDao.fetchOneByUsername("testUser");
//		fetchOneByUsername.setEmail("tt@gmail.com");
//		usersDao.delete(fetchOneByUsername);
		spring.jooq.dao.generated.tables.Users userAlias = spring.jooq.dao.generated.tables.Users.USERS.as("users");
		spring.jooq.dao.generated.tables.Address addressAlias = spring.jooq.dao.generated.tables.Address.ADDRESS.as("address");
		
		Result<Record> fetch = cxt.select().from(userAlias,addressAlias).where(userAlias.ADDRESS_ID.equal(addressAlias.ID).and(userAlias.USERNAME.equal("testUser"))).fetch();
		
		System.out.println(fetch);

	}

}
