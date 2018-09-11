package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @Description: 发送邮件
 * @author:	wangyanan
 * @date:	2017年12月5日 下午3:02:16
 */
public class SendEmailVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = -1836583467194146903L;
	private String title;//标题
	private String address;//收件地址
	private String tplKey;//模板关键字
	private Map map;//邮件内容
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTplKey() {
		return tplKey;
	}
	public void setTplKey(String tplKey) {
		this.tplKey = tplKey;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	

}
