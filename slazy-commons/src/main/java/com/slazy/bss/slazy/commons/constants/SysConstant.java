package com.slazy.bss.slazy.commons.constants;

public class SysConstant {

	public enum UserConstant {
		
		SUCCESS("0000", "请求成功"), DEFEAT("3000", "请求失败"),ISNULL("3001","数据不存在"), FILENULL("3002", "上传文件内容为空！"),
		OVERSIZE("3003", "图片尺寸不能超过2M!");
		
		private String code;
		private String message;
		
		public String getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		
		private UserConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
	}
	
	
	/**
	 * @author hc-3020-i3
	 *
	 */
	public enum SmsConstant {
		
		VERIFY("1","验证类"),MARKET("2","营销类"),INFORM("3","通知类");
		
		private String code;
		private String message;
		
		public String getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		private SmsConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
	}
	
	
	/**
	 * @author hc-3020-i3
	 *
	 */
	public enum SmsStatusConstant {
		
		SUCCESS("0000","发送成功"),DEFEAT("3000","发送失败"),TIMEOUT("3001","发送超时");
		
		private String code;
		private String message;
		
		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		private SmsStatusConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
	}
}
