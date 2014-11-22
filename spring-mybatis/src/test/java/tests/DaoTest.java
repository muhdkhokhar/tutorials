package tests;

import java.util.List;
import java.util.UUID;

import makky.mybatis.result.UserDetailDepartmentResult;
import makky.mybatis.tutorial.IUserMgmtDao;
import makky.mybatis.tutorial.domain.Department;

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

	@Test
	public void testCache() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			long start = System.currentTimeMillis();
			Integer countForDepartment = mgmtDao.getCountForDepartment("%%");
			System.out.println("\n Total count = " + countForDepartment);
			long end = System.currentTimeMillis();
			long totalMili = end - start;
			System.out.println("Total time = " + totalMili);
			Thread.sleep(2000);
			if(i%10==0){
				Department dept = new Department();
				dept.setDepartment_name(UUID.randomUUID().toString());
				mgmtDao.addDepartment(dept);
				System.out.println("\n=======================\nadded new dept...");
			}

		}
	}

	@Test
	public void testCRUD() {
		// Department dept=new Department();
		// dept.setDepartment_name(UUID.randomUUID().toString());
		// mgmtDao.addDepartment(dept);
		// System.out.println("The inserted id " + dept.getId());

		// int deleteDepartmentByName =
		// mgmtDao.deleteDepartmentByName("last department");
		// System.out.println("total deleted = " + deleteDepartmentByName);

		// int updateDepartmentNameById = mgmtDao.updateDepartmentNameById(
		// "new department", 3);
		// System.out.println("total updated = " + updateDepartmentNameById);
		for (int i = 0; i < 10000; i++) {
			Department dept = new Department();
			dept.setDepartment_name(UUID.randomUUID().toString());
			mgmtDao.addDepartment(dept);
			System.out.println("total added = " + i);
		}

	}
}
