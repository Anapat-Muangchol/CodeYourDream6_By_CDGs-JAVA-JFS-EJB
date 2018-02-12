package trainworkshop;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the job_title database table.
 * 
 */
@Entity
@Table(name="job_title")
@NamedQuery(name="JobTitle.findAll", query="SELECT j FROM JobTitle j")
public class JobTitle implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jobTitleCode;
	private String jobTitleName;
	private String jobType;
	private List<Employee> employees;

	public JobTitle() {
	}


	@Id
	@Column(name="job_title_code", unique=true, nullable=false, length=3)
	public String getJobTitleCode() {
		return this.jobTitleCode;
	}

	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}


	@Column(name="job_title_name", nullable=false, length=50)
	public String getJobTitleName() {
		return this.jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}


	@Column(name="job_type", nullable=false, length=1)
	public String getJobType() {
		return this.jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}


	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="jobTitle")
	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setJobTitle(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setJobTitle(null);

		return employee;
	}

}