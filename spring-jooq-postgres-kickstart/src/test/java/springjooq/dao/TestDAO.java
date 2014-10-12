package springjooq.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-config.xml" })
public class TestDAO {

	@Autowired
	private TestBean tbean;

	@Test
	public void testFetch() {
		Assert.assertNotNull(tbean);
		tbean.test();
	}

}
