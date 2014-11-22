package makky.mybatis.tutorial;

import java.util.List;

import makky.mybatis.result.UserDetailDepartmentResult;
import makky.mybatis.tutorial.domain.Department;

import org.apache.ibatis.annotations.Param;

public interface IUserMgmtDao {

	public Integer getCountForDepartment(final String departmentName);

	public Boolean isUserExistsForDepartment(
			@Param("deptName") final String deptName,
			@Param("username") final String username);

	public List<UserDetailDepartmentResult> getUserDetail(
			@Param("deptName") final String deptName,
			@Param("username") final String username);
	
	public void addDepartment(final Department dept);
	
	public int deleteDepartmentByName(final String name);
	
	public int updateDepartmentNameById(@Param("name") String deptName , @Param("id") Integer id );

}
