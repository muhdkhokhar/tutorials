package springjooq.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import spring.jooq.dao.generated.tables.daos.UserDetailsDao;
import spring.jooq.dao.generated.tables.daos.UserImagesDao;
import spring.jooq.dao.generated.tables.pojos.UserDetails;
import spring.jooq.dao.generated.tables.pojos.UserImages;
import spring.jooq.dao.generated.tables.records.UserImagesRecord;

@Transactional(rollbackFor = Exception.class)
public class UserDetailServiceImpl implements IUserDetailService {

	@Autowired
	private DSLContext cxt;
	private UserDetailsDao userDetailsDao;
	private UserImagesDao userImagesDao;

	public UserDetailServiceImpl() {

	}

	@PostConstruct
	public void initDAOS() {
		userDetailsDao = new UserDetailsDao(cxt.configuration());
		userImagesDao = new UserImagesDao(cxt.configuration());
	}

	@Override
	public void inserImageAndUser(UserDetails uDetail, UserImages uImage) {

		spring.jooq.dao.generated.tables.UserImages userImage = spring.jooq.dao.generated.tables.UserImages.USER_IMAGES
				.as("user_images");
		// first add image
		UserImagesRecord fetchOne = cxt.insertInto(userImage, userImage.IMAGE)
				.values(uImage.getImage()).returning(userImage.ID).fetchOne();

		uDetail.setImageId(fetchOne.getId());
		userDetailsDao.insert(uDetail);

	}

	@Override
	public List<UserDetails> getAllUsers() {
		return userDetailsDao.findAll();

	}

	@Override
	public UserImages findImage(Integer imageID) {
		return userImagesDao.findById(imageID);
	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	public UserImagesDao getUserImagesDao() {
		return userImagesDao;
	}

	public void setUserImagesDao(UserImagesDao userImagesDao) {
		this.userImagesDao = userImagesDao;
	}

}
