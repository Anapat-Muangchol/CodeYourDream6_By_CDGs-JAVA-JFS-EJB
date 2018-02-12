package th.co.cdg.train.workshop.bean;

import java.io.Serializable;

import th.co.cdg.train.workshop.entity.Employee;

public class SkillBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6752884766535280944L;
	private String skillId;
	private String employeeId;
	private String skillDesc;
	private String skillName;
	
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSkillDesc() {
		return skillDesc;
	}
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
}
