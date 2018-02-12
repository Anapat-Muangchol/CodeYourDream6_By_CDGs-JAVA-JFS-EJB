package th.co.cdg.train.workshop.persistence;

import java.util.List;

import javax.ejb.Local;

import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;

@Local
public interface HrQueryPersistenceLocal {
	public List<Department> queryDepartment();
	public List<JobTitle> queryJobTitle();
	public List<Employee> queryEmployeeByCondition(EmployeeBean employeeBean);
}
