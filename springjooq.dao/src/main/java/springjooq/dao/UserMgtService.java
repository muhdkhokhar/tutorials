package springjooq.dao;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import spring.jooq.dao.generated.tables.daos.UsersDao;
import spring.jooq.dao.generated.tables.pojos.Users;

@Transactional(rollbackFor = DataAccessException.class)
public class UserMgtService {

	@Autowired
	private DSLContext cxt;

	public void inserUser(Users user) {
		UsersDao usersDao = new UsersDao(cxt.configuration());
		usersDao.insert(user);

		// break
		usersDao.insert(user);

	}
}
