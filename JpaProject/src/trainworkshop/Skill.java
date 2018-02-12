package trainworkshop;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@Table(name="skill")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;
	private String skillId;
	private String skillDesc;
	private String skillName;
	private Employee employee;

	public Skill() {
	}


	@Id
	@Column(name="skill_id", unique=true, nullable=false, length=5)
	public String getSkillId() {
		return this.skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}


	@Column(name="skill_desc", length=200)
	public String getSkillDesc() {
		return this.skillDesc;
	}

	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}


	@Column(name="skill_name", nullable=false, length=50)
	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}


	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employee_id", nullable=false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}