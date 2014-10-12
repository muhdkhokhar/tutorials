package springjooq.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import spring.jooq.dao.generated.tables.daos.UsersDao;

public class TestBean {

	@Autowired
	private DSLContext cxt;

	public void test() {
		UsersDao usersDao = new UsersDao(cxt.configuration());
		System.out.println(usersDao.findAll());
	}

}
