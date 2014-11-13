package makky.mybatis.tutorial;

import java.util.List;

import makky.mybatis.result.UserDetailDepartmentResult;

import org.apache.ibatis.annotations.Param;

public interface IUserMgmtDao {

	public Integer getCountForDepartment(final String departmentName);

	public Boolean isUserExistsForDepartment(
			@Param("deptName") final String deptName,
			@Param("username") final String username);

	public List<UserDetailDepartmentResult> getUserDetail(
			@Param("deptName") final String deptName,
			@Param("username") final String username);

}
