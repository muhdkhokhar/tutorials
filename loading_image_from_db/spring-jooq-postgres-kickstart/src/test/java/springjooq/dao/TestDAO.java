package springjooq.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.jooq.dao.generated.tables.pojos.UserDetails;
import spring.jooq.dao.generated.tables.pojos.UserImages;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-config.xml" })
public class TestDAO {

	@Autowired
	private IUserDetailService detailService;

	@Test
	public void testInsertImage() throws IOException {
		InputStream stream = TestDAO.class.getResourceAsStream("/Fronalpstock_big.jpg");
		UserDetails detail = new UserDetails();
		detail.setAddress("David Guettta address");
		detail.setUsername("dguetta");

		UserImages images = new UserImages();
		images.setImage(IOUtils.toByteArray(stream));

		detailService.inserImageAndUser(detail, images);
	}

}
