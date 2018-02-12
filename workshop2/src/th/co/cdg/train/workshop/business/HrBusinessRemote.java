package th.co.cdg.train.workshop.business;

import javax.ejb.Remote;

import th.co.cdg.train.workshop.bean.EmployeeBean;

@Remote
public interface HrBusinessRemote {
	public EmployeeBean insertEmployee(EmployeeBean employeeBean);
	public EmployeeBean updateEmployee(EmployeeBean employeeBean);
	public void deleteEmployee(String employeeId);
}
