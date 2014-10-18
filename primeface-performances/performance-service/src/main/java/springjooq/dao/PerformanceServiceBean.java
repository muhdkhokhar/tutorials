package springjooq.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import spring.jooq.dao.generated.tables.daos.UserDetailsDao;
import spring.jooq.dao.generated.tables.daos.UserLoginDao;
import spring.jooq.dao.generated.tables.pojos.UserDetails;
import spring.jooq.dao.generated.tables.pojos.UserLogin;

public class PerformanceServiceBean implements IGenericService {

	@Autowired
	private DSLContext context;
	private UserLoginDao userLoginDao;
	private UserDetailsDao userDetailsDao;

	@PostConstruct
	public void init() {
		userLoginDao = new UserLoginDao(context.configuration());
		userDetailsDao = new UserDetailsDao(context.configuration());
	}

	public boolean isUserValid(final String user) {
		List<UserLogin> fetchByUsername = userLoginDao.fetchByUsername(user);
		if (fetchByUsername != null && !fetchByUsername.isEmpty()) {
			return true;
		}
		return false;
	}

	public void addUser(final UserDetails details) {
		userDetailsDao.insert(details);
	}

	public List<UserDetails> getAllUsers() {
		List<UserDetails> findAll = userDetailsDao.findAll();
		if (findAll != null && findAll.size() > 100) {
			return findAll.subList(0, 10);
		} else {
			return findAll;
		}

	}

	public UserLoginDao getUserLoginDao() {
		return userLoginDao;
	}

	public void setUserLoginDao(UserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
}
