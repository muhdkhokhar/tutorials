package primefaces.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import spring.jooq.dao.generated.tables.pojos.UserDetails;
import springjooq.dao.IUserDetailService;

@Controller
@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable{

	@Autowired
	private IUserDetailService detailService;

	private List<UserDetails> userDetails;

	public UserBean() {
		userDetails = new ArrayList<UserDetails>();
	}

	public void fetchAllUsers(ActionEvent event) {

		userDetails.clear();

		userDetails.addAll(detailService.getAllUsers());

	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

}
