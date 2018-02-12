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
import th.co.cdg.train.workshop.convert.ConvertObjects;
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
	
	private ConvertObjects convert = new ConvertObjects();
	
	//==================== methods implemented ====================
	
	@Override
	public List<DepartmentBean> queryDepartment() {
		List<Department> deptList = hrQueryPersistence.queryDepartment();

		if (deptList != null && !deptList.isEmpty()) {
			List<DepartmentBean> deptBeanList = new ArrayList<DepartmentBean>();
			for (Department dept : deptList) {
				DepartmentBean deptBean = convert.toDepartmentBean(dept);
				deptBeanList.add(deptBean);
			}
			return deptBeanList;
		}

		return null;
	}

	@Override
	public List<JobTitleBean> queryJobTitle() {
		List<JobTitle> jobList = hrQueryPersistence.queryJobTitle();

		if (jobList != null && !jobList.isEmpty()) {
			List<JobTitleBean> jobBeanList = new ArrayList<JobTitleBean>();
			for (JobTitle job : jobList) {
				JobTitleBean jobBean = convert.toJobTitleBean(job);
				jobBeanList.add(jobBean);
			}
			return jobBeanList;
		}

		return null;
	}

	@Override
	public List<EmployeeBean> queryEmployeeByCondition(EmployeeBean employeeBean) {
		List<Employee> employees = hrQueryPersistence.queryEmployeeByCondition(employeeBean);

		if (employees != null && !employees.isEmpty()) {
			List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
			for (Employee e : employees) {

				EmployeeBean eBean = convert.toEmployeeBean(e);

				if (e.getSkills() != null && !e.getSkills().isEmpty()) {
					List<SkillBean> sbList = new ArrayList<SkillBean>();
					for (Skill skill : e.getSkills()) {
						SkillBean s = convert.toSkillBean(skill);
						sbList.add(s);
					}
					eBean.setSkillBeans(sbList);
				}

				employeeBeans.add(eBean);
			}
			return employeeBeans;
		}

		return null;
	}

	@Override
	public EmployeeBean queryEmployeeAndSkillById(String employeeId) {
		EmployeeBean eBean = queryEmployeeById(employeeId);
		if (eBean != null) {

			eBean.setSkillBeans(querySkillByEmployeeId(employeeId));

			return eBean;
		}
		return null;
	}

	@Override
	public EmployeeBean queryEmployeeById(String employeeId) {
		Employee e = hrQueryPersistence.queryEmployeeById(employeeId);

		EmployeeBean eBean = convert.toEmployeeBean(e);

		return eBean;
	}

	@Override
	public List<SkillBean> querySkillByEmployeeId(String employeeId) {
		List<Skill> skills = hrQueryPersistence.querySkillByEmployeeId(employeeId);

		List<SkillBean> skillBeanList = new ArrayList<SkillBean>();

		for (Skill skill : skills) {
			SkillBean sBean = convert.toSkillBean(skill);
			skillBeanList.add(sBean);
		}

		return skillBeanList;
	}

}
