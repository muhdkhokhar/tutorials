package primefaces.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.jooq.dao.generated.tables.pojos.UserDetails;
import springjooq.dao.PerformanceServiceBean;

@Controller
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<UserDetails> userDetails;

	private static Random random;

	static {
		random = new Random();
	}

	private Logger LOG = LoggerFactory.getLogger(UserBean.class);

	public UserBean() {

	}

	@Autowired
	private PerformanceServiceBean performanceServiceBean;

	@PostConstruct
	public void init() {
		LOG.info("Inititialising userbean");

		userDetails = performanceServiceBean.getAllUsers();

	}

	public void addUser(ActionEvent event) {
		LOG.info("adding user");
		UserDetails details = new UserDetails();
		details.setAddress("Test Address");
		details.setAge(random.nextInt(100));
		details.setUserName("makky-"
				+ UUID.randomUUID().getLeastSignificantBits());
		performanceServiceBean.addUser(details);
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

}
