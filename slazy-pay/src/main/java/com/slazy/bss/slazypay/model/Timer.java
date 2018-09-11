package com.slazy.bss.slazypay.model;


/**
 * 
 * @Description: 定时器模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:43:07
 */
public class Timer extends BaseModel {

	private static final long serialVersionUID = 4934564629047808819L;

	private String operator;//操作人
	private String status;//状态
	private String remark;//备注
	private String jobClass;//定时器类
	private String jobName;//定时器名称
	private String rule;//执行规则
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
}

