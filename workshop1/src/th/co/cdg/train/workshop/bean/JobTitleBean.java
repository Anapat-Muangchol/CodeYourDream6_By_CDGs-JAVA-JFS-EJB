package th.co.cdg.train.workshop.bean;

import java.io.Serializable;

public class JobTitleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7144477968382108538L;
	private String jobTitleCode;
	private String jobTitleName;
	private String jobType;
	
	public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
	public String getJobTitleName() {
		return jobTitleName;
	}
	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
}
