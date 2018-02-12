package th.co.cdg.train.workshop.persistence;

import javax.ejb.Local;

import th.co.cdg.train.workshop.entity.Employee;

@Local
public interface HrPersistenceLocal {
	public Employee insertEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public void deleteEmployee(String employeeId);
}
