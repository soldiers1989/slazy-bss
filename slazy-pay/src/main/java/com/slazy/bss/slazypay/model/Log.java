package com.slazy.bss.slazypay.model;


/**
 * 
 * @Description: 消息日志模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:47:36
 */
public class Log extends BaseModel {

	private static final long serialVersionUID = -7090849729361993144L;

	private String msgId;//消息ID
	private String srcSystem;//源系统
	private String destSystem;//目标系统
	private String destInterface;//目标接口
	private String resCode;//响应码
	private String resInfo;//响应信息
	private String jsonParam;//参数
	private Integer timeConsuming;//耗时
	private String type;//类型
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getSrcSystem() {
		return srcSystem;
	}
	public void setSrcSystem(String srcSystem) {
		this.srcSystem = srcSystem;
	}
	public String getDestSystem() {
		return destSystem;
	}
	public void setDestSystem(String destSystem) {
		this.destSystem = destSystem;
	}
	public String getDestInterface() {
		return destInterface;
	}
	public void setDestInterface(String destInterface) {
		this.destInterface = destInterface;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResInfo() {
		return resInfo;
	}
	public void setResInfo(String resInfo) {
		this.resInfo = resInfo;
	}
	public String getJsonParam() {
		return jsonParam;
	}
	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}
	public int getTimeConsuming() {
		return timeConsuming;
	}
	public void setTimeConsuming(int timeConsuming) {
		this.timeConsuming = timeConsuming;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
