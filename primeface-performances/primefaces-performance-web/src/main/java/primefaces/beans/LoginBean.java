package primefaces.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import springjooq.dao.PerformanceServiceBean;

@Controller
@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger LOG = LoggerFactory.getLogger(LoginBean.class);

	@Autowired
	private PerformanceServiceBean performanceServiceBean;

	private String username;
	private String password;

	public String login() {
		LOG.info("Checking if user is valid = " + this.username);
		boolean isUserValid = performanceServiceBean.isUserValid(username);
		LOG.info("User is valid = " + isUserValid);

		return "success";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
