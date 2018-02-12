package th.co.cdg.train.workshop.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;
import th.co.cdg.train.workshop.bean.SkillBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.entity.Skill;
import th.co.cdg.train.workshop.persistence.HrQueryPersistenceLocal;

/**
 * Session Bean implementation class HrQueryImpl
 */
@Stateless
@LocalBean
public class HrQueryImpl implements HrQueryRemote, HrQueryLocal {
	
	@EJB
	private HrQueryPersistenceLocal hrQueryPersistence;
	
	@Override
	public List<DepartmentBean> queryDepartment() {
		List<Department> deptList = hrQueryPersistence.queryDepartment();
		
		if(deptList != null && !deptList.isEmpty()){
			List<DepartmentBean> deptBeanList = new ArrayList<DepartmentBean>();
			for (Department dept : deptList){
				DepartmentBean deptBean = new DepartmentBean();
				deptBean.setDepartmentCode(dept.getDepartmentCode());
				deptBean.setDepartmentName(dept.getDepartmentName());
				deptBeanList.add(deptBean);
			}
			return deptBeanList;
		}
		
		return null;
	}

	@Override
	public List<JobTitleBean> queryJobTitle() {
		List<JobTitle> jobList = hrQueryPersistence.queryJobTitle();
		
		if(jobList != null && !jobList.isEmpty()){
			List<JobTitleBean> jobBeanList = new ArrayList<JobTitleBean>();
			for (JobTitle job : jobList){
				JobTitleBean jobBean = new JobTitleBean();
				jobBean.setJobTitleCode(job.getJobTitleCode());
				jobBean.setJobTitleName(job.getJobTitleName());
				jobBean.setJobType(job.getJobType());
				jobBeanList.add(jobBean);
			}
			return jobBeanList;
		}
		
		return null;
	}

	@Override
	public List<EmployeeBean> queryEmployeeByCondition(EmployeeBean employeeBean) {
		List<Employee> employees = hrQueryPersistence.queryEmployeeByCondition(employeeBean);
		
		if(employees != null && !employees.isEmpty()){
			List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
			for (Employee e : employees){
				EmployeeBean eBean = new EmployeeBean();
				eBean.setEmployeeId(e.getEmployeeId());
				eBean.setTitle(e.getTitle());
				eBean.setFirstName(e.getFirstName());
				eBean.setLastName(e.getLastName());
				eBean.setGender(e.getGender());
				
				eBean.setDepartmentBean(new DepartmentBean());
				eBean.getDepartmentBean().setDepartmentCode(e.getDepartment().getDepartmentCode());
				eBean.getDepartmentBean().setDepartmentName(e.getDepartment().getDepartmentName());
				
				eBean.setJobTitleBean(new JobTitleBean());
				eBean.getJobTitleBean().setJobTitleCode(e.getJobTitle().getJobTitleCode());
				eBean.getJobTitleBean().setJobTitleName(e.getJobTitle().getJobTitleName());
				eBean.getJobTitleBean().setJobType(e.getJobTitle().getJobType());
				
				eBean.setAddress(e.getAddress());
				
				if(e.getSkills() != null && !e.getSkills().isEmpty()){
					eBean.setSkillBeans(new ArrayList<SkillBean>());
					for (Skill skill : e.getSkills()) {
						SkillBean s = new SkillBean();
						s.setSkillId(skill.getSkillId());
						s.setEmployeeId(skill.getEmployee().getEmployeeId());
						s.setSkillName(skill.getSkillName());
						s.setSkillDesc(skill.getSkillDesc());
						eBean.getSkillBeans().add(s);
					}
				}
				
				employeeBeans.add(eBean);
			}
			return employeeBeans;
		}
		
		return null;
	}
	
    
}
