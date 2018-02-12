package th.co.cdg.train.workshop.convert;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.bean.SkillBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.entity.Skill;

public class ConvertObjects {
	
	// ==================== Entity to Bean ====================
	
	public EmployeeBean toEmployeeBean(Employee e) {
		EmployeeBean eBean = new EmployeeBean();
		eBean.setEmployeeId(e.getEmployeeId());
		eBean.setTitle(e.getTitle());
		eBean.setFirstName(e.getFirstName());
		eBean.setLastName(e.getLastName());
		eBean.setGender(e.getGender());
		eBean.setAddress(e.getAddress());

		eBean.setDepartmentBean(toDepartmentBean(e.getDepartment()));

		eBean.setJobTitleBean(toJobTitleBean(e.getJobTitle()));

		return eBean;
	}

	public DepartmentBean toDepartmentBean(Department d) {
		DepartmentBean deptBean = new DepartmentBean();
		deptBean.setDepartmentCode(d.getDepartmentCode());
		deptBean.setDepartmentName(d.getDepartmentName());
		return deptBean;
	}

	public JobTitleBean toJobTitleBean(JobTitle j) {
		JobTitleBean jobBean = new JobTitleBean();
		jobBean.setJobTitleCode(j.getJobTitleCode());
		jobBean.setJobTitleName(j.getJobTitleName());
		jobBean.setJobType(j.getJobType());
		return jobBean;
	}

	public SkillBean toSkillBean(Skill s) {
		SkillBean skillBean = new SkillBean();
		skillBean.setSkillId(s.getSkillId());
		skillBean.setEmployeeId(s.getEmployee().getEmployeeId());
		skillBean.setSkillName(s.getSkillName());
		skillBean.setSkillDesc(s.getSkillDesc());
		return skillBean;
	}
	
	
	// ==================== Bean to Entity ====================
	
	public Employee toEmployee(EmployeeBean eBean) {
		Employee e = new Employee();
		e.setEmployeeId(eBean.getEmployeeId());
		e.setTitle(eBean.getTitle());
		e.setFirstName(eBean.getFirstName());
		e.setLastName(eBean.getLastName());
		e.setGender(eBean.getGender());
		e.setAddress(eBean.getAddress());
		
		e.setDepartment(toDepartment(eBean.getDepartmentBean()));
		e.setJobTitle(toJobTitle(eBean.getJobTitleBean()));

		return e;
	}

	public Department toDepartment(DepartmentBean d) {
		Department dept = new Department();
		dept.setDepartmentCode(d.getDepartmentCode());
		dept.setDepartmentName(d.getDepartmentName());
		return dept;
	}

	public JobTitle toJobTitle(JobTitleBean j) {
		JobTitle job = new JobTitle();
		job.setJobTitleCode(j.getJobTitleCode());
		job.setJobTitleName(j.getJobTitleName());
		job.setJobType(j.getJobType());
		return job;
	}

	public Skill toSkill(SkillBean s) {
		Skill skill = new Skill();
		skill.setSkillId(s.getSkillId());
		skill.setSkillName(s.getSkillName());
		skill.setSkillDesc(s.getSkillDesc());
		
		Employee employee = new Employee();
		employee.setEmployeeId(s.getEmployeeId());
		skill.setEmployee(employee);
		
		return skill;
	}
}
