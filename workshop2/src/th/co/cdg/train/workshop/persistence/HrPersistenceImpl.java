package th.co.cdg.train.workshop.persistence;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdg.train.workshop.entity.Employee;

/**
 * Session Bean implementation class HrPersistenceImpl
 */
@Stateless
@LocalBean
public class HrPersistenceImpl implements HrPersistenceLocal {
	
	@PersistenceContext(unitName = "trainworkshop")
	private EntityManager em;
	
	private HrQueryPersistenceLocal hrQueryPersistenceLocal;
	
	@Override
	public Employee insertEmployee(Employee employee) {
		if (employee != null) {
			String newEmpId = hrQueryPersistenceLocal.queryNextEmployeeId();
			employee.setEmployeeId(newEmpId);
			em.persist(employee);
			return employee;
		}
		
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if (employee != null) {
			em.merge(employee);
			return employee;
		}
		
		return employee;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		if (employeeId != null && !"".equals(employeeId)) {
			em.remove(em.find(Employee.class, employeeId));
		}
	}

}
