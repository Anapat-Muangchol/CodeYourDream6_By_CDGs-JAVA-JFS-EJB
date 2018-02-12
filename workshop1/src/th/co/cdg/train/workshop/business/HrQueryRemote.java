package th.co.cdg.train.workshop.business;

import java.util.List;

import javax.ejb.Remote;

import th.co.cdg.train.workshop.bean.DepartmentBean;
import th.co.cdg.train.workshop.bean.EmployeeBean;
import th.co.cdg.train.workshop.bean.JobTitleBean;

@Remote
public interface HrQueryRemote {
	public List<DepartmentBean> queryDepartment();
	public List<JobTitleBean> queryJobTitle();
	public List<EmployeeBean> queryEmployeeByCondition(EmployeeBean employeeBean);

}
