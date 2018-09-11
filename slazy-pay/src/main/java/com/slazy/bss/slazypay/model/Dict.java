package com.slazy.bss.slazypay.model;


/**
 * 
 * @Description: 字典模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:37:26
 */
public class Dict extends BaseModel {

	private static final long serialVersionUID = 7086576216170686298L;

	private String status;//状态
	private String remark;//备注
	private String operator;//操作人
	private String displayName;//名称
	private String dictKey;//关键字
	private String path;//路径
	private String value1;//值1
	private String value2;//值2
	private String value3;//值3
	private String value4;//值4
	private String value5;//值5
	
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getValue5() {
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	
	
}
