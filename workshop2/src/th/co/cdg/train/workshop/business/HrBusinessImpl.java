package th.co.cdg.train.workshop.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.convert.ConvertObjects;
import th.co.cdg.train.workshop.entity.Department;
import th.co.cdg.train.workshop.entity.Employee;
import th.co.cdg.train.workshop.entity.JobTitle;
import th.co.cdg.train.workshop.persistence.HrPersistenceLocal;

/**
 * Session Bean implementation class HrBusinessImpl
 */
@Stateless
@LocalBean
public class HrBusinessImpl implements HrBusinessRemote, HrBusinessLocal {

	@EJB
	private HrPersistenceLocal hrPersistenceLocal;

	private ConvertObjects convert = new ConvertObjects();
	
	// ==================== methods implemented ====================

	@Override
	public EmployeeBean insertEmployee(EmployeeBean employeeBean) {
		if (employeeBean != null) {
			Employee employee = convert.toEmployee(employeeBean);
			Employee employeeFromDb = hrPersistenceLocal.insertEmployee(employee);
			return convert.toEmployeeBean(employeeFromDb);
		}
		return null;
	}

	@Override
	public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
		if (employeeBean != null) {
			Employee employee = convert.toEmployee(employeeBean);
			Employee employeeFromDb = hrPersistenceLocal.updateEmployee(employee);
			return convert.toEmployeeBean(employeeFromDb);
		}
		return null;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		if (employeeId != null && !"".equals(employeeId)) {
			hrPersistenceLocal.deleteEmployee(employeeId);
		}
	}

}
