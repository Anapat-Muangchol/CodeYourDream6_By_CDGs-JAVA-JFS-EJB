package th.co.cdg.train.workshop.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;

/**
 * Session Bean implementation class HrQueryPersistenceImpl
 */
@Stateless
@LocalBean
public class HrQueryPersistenceImpl implements HrQueryPersistenceLocal {
	
	@PersistenceContext(unitName = "trainworkshop")
	private EntityManager em;
	
	@Override
	public List<Department> queryDepartment() {
		final String jpql = "select d from Department d order by d.departmentCode";
		Query query = em.createQuery(jpql);
		return (List<Department>) query.getResultList();
	}

	@Override
	public List<JobTitle> queryJobTitle() {
		final String jpql = "select j from JobTitle j order by j.jobTitleCode";
		Query query = em.createQuery(jpql);
		return (List<JobTitle>) query.getResultList();
	}

	@Override
	public List<Employee> queryEmployeeByCondition(EmployeeBean employeeBean) {
		if(employeeBean == null || 
				((employeeBean.getDepartmentBean().getDepartmentCode() == null || "".equals(employeeBean.getDepartmentBean().getDepartmentCode())) &&
					(employeeBean.getJobTitleBean().getJobTitleCode() == null || "".equals(employeeBean.getJobTitleBean().getJobTitleCode())) &&
					(employeeBean.getJobTitleBean().getJobType() == null || "".equals(employeeBean.getJobTitleBean().getJobType())) &&
					(employeeBean.getFirstName() == null || "".equals(employeeBean.getFirstName())) &&
					(employeeBean.getLastName() == null || "".equals(employeeBean.getLastName())) &&
					(employeeBean.getGender() == null || "".equals(employeeBean.getGender()))
				)
			){
			throw new IllegalArgumentException(" The all parameter is blank or null.");
		}
		
//		//====== JPQL Version ======
//		final StringBuilder jpql = new StringBuilder();
//		jpql.append("select e"
//				+ " from Employee e"
//				+ " join fetch e.department d"
//				+ " join fetch e.jobTitle j"
//				+ " where 1=1");
//		
//		if(employeeBean.getDepartmentBean().getDepartmentCode() != null && !"".equals(employeeBean.getDepartmentBean().getDepartmentCode())){
//			jpql.append(" and e.departmentCode = :departmentCode");
//		}
//		if(employeeBean.getJobTitleBean().getJobTitleCode() != null && !"".equals(employeeBean.getJobTitleBean().getJobTitleCode())){
//			jpql.append(" and e.jobTitleCode = :jobTitleCode");
//		}
//		if(employeeBean.getJobTitleBean().getJobType() != null && !"".equals(employeeBean.getJobTitleBean().getJobType())){
//			jpql.append(" and j.jobType = :jobType");
//		}
//		if(employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())){
//			jpql.append(" and e.firstName like :firstName");
//		}
//		if(employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())){
//			jpql.append(" and e.lastName like :lastName");
//		}
//		if(employeeBean.getGender() != null && !"".equals(employeeBean.getGender())){
//			jpql.append(" and e.gender = :gender");
//		}
//		
//		jpql.append(" order by e.employeeId");
//		
//		Query query = em.createQuery(jpql.toString());
//		
//		if(employeeBean.getDepartmentBean().getDepartmentCode() != null && !"".equals(employeeBean.getDepartmentBean().getDepartmentCode())){
//			query.setParameter("departmentCode", employeeBean.getDepartmentBean().getDepartmentCode());
//		}
//		if(employeeBean.getJobTitleBean().getJobTitleCode() != null && !"".equals(employeeBean.getJobTitleBean().getJobTitleCode())){
//			query.setParameter("jobTitleCode", employeeBean.getJobTitleBean().getJobTitleCode());
//		}
//		if(employeeBean.getJobTitleBean().getJobType() != null && !"".equals(employeeBean.getJobTitleBean().getJobType())){
//			query.setParameter("jobType", employeeBean.getJobTitleBean().getJobType());
//		}
//		if(employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())){
//			query.setParameter("firstName", "%" + employeeBean.getFirstName() + "%");
//		}
//		if(employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())){
//			query.setParameter("lastName", "%" + employeeBean.getLastName() + "%");
//		}
//		if(employeeBean.getGender() != null && !"".equals(employeeBean.getGender())){
//			query.setParameter("gender", employeeBean.getGender());
//		}
//		
//		return (List<Employee>) query.getResultList();
		
		
		
		// ====== Native SQL Version ======
		final StringBuilder sql = new StringBuilder();
		sql.append("select e.employee_id, e.title, e.first_name, e.last_name, e.gender, e.department_code, e.job_title_code, d.department_name, j.job_title_name, j.job_type"
					+ " from employee e"
					+ " join department d on (e.department_code = d.department_code)"
					+ " join job_title j on (e.job_title_code = j.job_title_code)"
					+ " where 1=1");
		
		if(employeeBean.getDepartmentBean().getDepartmentCode() != null && !"".equals(employeeBean.getDepartmentBean().getDepartmentCode())){
			sql.append(" and e.department_code = :departmentCode");
		}
		if(employeeBean.getJobTitleBean().getJobTitleCode() != null && !"".equals(employeeBean.getJobTitleBean().getJobTitleCode())){
			sql.append(" and e.job_title_code = :jobTitleCode");
		}
		if(employeeBean.getJobTitleBean().getJobType() != null && !"".equals(employeeBean.getJobTitleBean().getJobType())){
			sql.append(" and j.job_type = :jobType");
		}
		if(employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())){
			sql.append(" and e.first_name like :firstName");
		}
		if(employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())){
			sql.append(" and e.last_name like :lastName");
		}
		if(employeeBean.getGender() != null && !"".equals(employeeBean.getGender())){
			sql.append(" and e.gender = :gender");
		}
		
		sql.append(" order by e.employee_id");
		System.out.println(sql.toString());
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(employeeBean.getDepartmentBean().getDepartmentCode() != null && !"".equals(employeeBean.getDepartmentBean().getDepartmentCode())){
			query.setParameter("departmentCode", employeeBean.getDepartmentBean().getDepartmentCode());
		}
		if(employeeBean.getJobTitleBean().getJobTitleCode() != null && !"".equals(employeeBean.getJobTitleBean().getJobTitleCode())){
			query.setParameter("jobTitleCode", employeeBean.getJobTitleBean().getJobTitleCode());
		}
		if(employeeBean.getJobTitleBean().getJobType() != null && !"".equals(employeeBean.getJobTitleBean().getJobType())){
			query.setParameter("jobType", employeeBean.getJobTitleBean().getJobType());
		}
		if(employeeBean.getFirstName() != null && !"".equals(employeeBean.getFirstName())){
			query.setParameter("firstName", "%" + employeeBean.getFirstName() + "%");
		}
		if(employeeBean.getLastName() != null && !"".equals(employeeBean.getLastName())){
			query.setParameter("lastName", "%" + employeeBean.getLastName() + "%");
		}
		if(employeeBean.getGender() != null && !"".equals(employeeBean.getGender())){
			query.setParameter("gender", employeeBean.getGender());
		}
		
		Object o = query.getResultList();
		
		if ( o != null ) {
			
			List<Employee> emps = new ArrayList<Employee>();
			if ( o instanceof Object[]) {
				Object[] resultObject = (Object[])o;
				Employee e = new Employee();
				e.setEmployeeId((String)resultObject[0]);
				e.setTitle((String)resultObject[1]);
				e.setFirstName((String)resultObject[2]);
				e.setLastName((String)resultObject[3]);
				e.setGender((String)resultObject[4]);
				
				e.setDepartment(new Department());
				e.getDepartment().setDepartmentCode((String)resultObject[5]);

				e.setJobTitle(new JobTitle());
				e.getJobTitle().setJobTitleCode((String)resultObject[6]);
				
				e.getDepartment().setDepartmentName((String)resultObject[7]);
				e.getJobTitle().setJobTitleName((String)resultObject[8]);
				
				emps.add(e);
			}else {
				List<Object[]> resultObjects = (List<Object[]>)o;
				for (Object[] resultObject : resultObjects) {
					Employee e = new Employee();
					e.setEmployeeId((String)resultObject[0]);
					e.setTitle((String)resultObject[1]);
					e.setFirstName((String)resultObject[2]);
					e.setLastName((String)resultObject[3]);
					e.setGender((String)resultObject[4]);
					
					e.setDepartment(new Department());
					
					e.getDepartment().setDepartmentCode((String)resultObject[5]);

					e.setJobTitle(new JobTitle());
					
					e.getJobTitle().setJobTitleCode((String)resultObject[6]);
					e.getDepartment().setDepartmentName((String)resultObject[7]);
					e.getJobTitle().setJobTitleName((String)resultObject[8]);
					e.getJobTitle().setJobType((String)resultObject[9]);
					
					emps.add(e);
				}
			}
			
			return emps;
		}
		
		return null;
		
	}

	

}
