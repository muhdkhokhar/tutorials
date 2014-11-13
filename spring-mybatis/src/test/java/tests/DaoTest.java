package tests;

import java.util.List;

import makky.mybatis.result.UserDetailDepartmentResult;
import makky.mybatis.tutorial.IUserMgmtDao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class DaoTest {

	@Autowired
	private IUserMgmtDao mgmtDao;

	@Test
	public void testDAO() {

		Assert.assertNotNull(mgmtDao);

		Integer countForDepartment = mgmtDao.getCountForDepartment("%Supp%");

		Assert.assertEquals(new Integer(2), countForDepartment);

		Boolean result = mgmtDao.isUserExistsForDepartment("IT", "matthew");

		Assert.assertEquals(true, result);

		result = mgmtDao.isUserExistsForDepartment("Support", "matthew");

		Assert.assertEquals(false, result);

		List<UserDetailDepartmentResult> userDetail = mgmtDao.getUserDetail(
				"IT", "matthew");

		for (UserDetailDepartmentResult userDetailDepartmentResult : userDetail) {
			System.out.println(userDetailDepartmentResult.getUsername());
			System.out.println(userDetailDepartmentResult.getDate_added());
		}

	}
}
