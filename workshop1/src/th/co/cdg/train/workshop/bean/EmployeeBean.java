package th.co.cdg.train.workshop.bean;

import java.io.Serializable;
import java.util.List;

public class EmployeeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3822529288975322426L;
	private String employeeId;
	private String title;
	private String firstName;
	private String lastName;
	private String gender;
	private DepartmentBean departmentBean;
	private JobTitleBean jobTitleBean;
	private String address;
	private List<SkillBean> skillBeans;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}
	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}
	public JobTitleBean getJobTitleBean() {
		return jobTitleBean;
	}
	public void setJobTitleBean(JobTitleBean jobTitleBean) {
		this.jobTitleBean = jobTitleBean;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<SkillBean> getSkillBeans() {
		return skillBeans;
	}
	public void setSkillBeans(List<SkillBean> skillBeans) {
		this.skillBeans = skillBeans;
	}
	
}
