package th.co.cdg.train.workshop.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.bean.SkillBean;
import th.co.cdg.train.workshop.business.HrBusinessLocal;
import th.co.cdg.train.workshop.business.HrQueryLocal;

@ManagedBean
@SessionScoped
public class EmployeeEditCtrl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -983984502531784289L;

	@EJB
	private HrQueryLocal hrQuery;

	@EJB
	private HrBusinessLocal hrBusiness;

	private EmployeeBean employee;
	private boolean isEdit;

	private List<SkillBean> skills;
	
	private String empId;
	
	public EmployeeBean getEmployee() {
		if (employee == null) {
			employee = new EmployeeBean();
			employee.setDepartmentBean(new DepartmentBean());
			employee.setJobTitleBean(new JobTitleBean());
			employee.setSkillBeans(new ArrayList<SkillBean>());
		}
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public List<SkillBean> getSkills() {
		if (skills == null) {
			skills = new ArrayList<SkillBean>();
		}
		return skills;
	}

	public void setSkills(List<SkillBean> skills) {
		this.skills = skills;
	}

	public String add() {
		employee = new EmployeeBean();
		employee.setDepartmentBean(new DepartmentBean());
		employee.setJobTitleBean(new JobTitleBean());
		employee.setSkillBeans(new ArrayList<SkillBean>());
		
		isEdit = false;
		return "edit";
	}

	public String edit(String empId) {
		this.empId = empId;
		System.out.println("empId : "+empId);
		isEdit = true;
		employee = hrQuery.queryEmployeeById(empId);
		
		return "edit";
	}

	public void remove(String empId) {
		System.out.println("empId : "+empId);
		hrBusiness.deleteEmployee(empId);
	}

	public void submit() {
		if(employee.getEmployeeId() == null || "".equals(employee.getEmployeeId())){
			hrBusiness.insertEmployee(employee);
			
		}else{
			employee = hrBusiness.updateEmployee(employee);
		}
	}

	public void clear() {
		if(isEdit){
			employee = hrQuery.queryEmployeeById(empId);
		}else{
			employee = new EmployeeBean();
			employee.setDepartmentBean(new DepartmentBean());
			employee.setJobTitleBean(new JobTitleBean());
			employee.setSkillBeans(new ArrayList<SkillBean>());
			
			skills = new ArrayList<SkillBean>();
		}
	}

	public String back() {
		return "index";
	}
}
