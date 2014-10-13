package springjooq.dao;

import java.util.List;

import spring.jooq.dao.generated.tables.pojos.UserDetails;
import spring.jooq.dao.generated.tables.pojos.UserImages;

public interface IUserDetailService {

	public List<UserDetails> getAllUsers();

	public UserImages findImage(final Integer imageID);
	
	public void inserImageAndUser(UserDetails uDetail,UserImages uImage);
}
