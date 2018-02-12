package th.co.cdg.train.workshop.manageBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.business.HrQueryLocal;

@ManagedBean
public class EmployeeController {
	
	@EJB
	private HrQueryLocal hrQuery;
	
	private List<DepartmentBean> departments;
	private List<JobTitleBean> jobTitles;
	private EmployeeBean employee;
	
	private List<EmployeeBean> employees;
	
	//================ Getter & Setter ================
	
	public List<DepartmentBean> getDepartments() {
		if(departments == null){
			departments = hrQuery.queryDepartment();
		}
		return departments;
	}
	public void setDepartments(List<DepartmentBean> departments) {
		this.departments = departments;
	}

	public List<JobTitleBean> getJobTitles() {
		if(jobTitles == null){
			jobTitles = hrQuery.queryJobTitle();
		}
		return jobTitles;
	}
	public void setJobTitles(List<JobTitleBean> jobTitles) {
		this.jobTitles = jobTitles;
	}
	
	public EmployeeBean getEmployee() {
		if(employee == null){
			employee = new EmployeeBean();
			employee.setDepartmentBean(new DepartmentBean());
			employee.setJobTitleBean(new JobTitleBean());
		}
		return employee;
	}
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}
	
	public List<EmployeeBean> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeBean> employees) {
		this.employees = employees;
	}
	
	//================ Methods ================
	
	public void clearForm(){
		employee = new EmployeeBean();
	}
	
	public void search(){
//		System.out.println("JobType : "+employee.getJobTitleBean().getJobType());
		List<EmployeeBean> employeesTemp = hrQuery.queryEmployeeByCondition(employee);
		if(employeesTemp != null && !employeesTemp.isEmpty()){
			employees = employeesTemp;
		}else{
			employees = new ArrayList<EmployeeBean>();
		}
//		System.out.println("JobType : "+employees.get(0).getJobTitleBean().getJobType());
	}
}
