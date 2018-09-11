package org.slazyframework.constants;

public class SysConstant {

	public enum UserConstant {

		SUCCESS("0000", "请求成功"), DEFEAT("3000", "请求失败"), ISNULL("3001", "数据不存在"), FILENULL("3002",
				"上传文件内容为空！"), OVERSIZE("3003", "图片尺寸不能超过2M!");

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

		VERIFY("1", "验证类"), MARKET("2", "营销类"), INFORM("3", "通知类");

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

		SUCCESS("0000", "发送成功"), DEFEAT("3000", "发送失败"), TIMEOUT("3001", "发送超时");

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

	/**
	 * 订单状态描述
	 * 
	 * @author hc-3020-i3
	 *
	 */
	public enum OrderStatusConstant {

		AWAIT("1001", "待支付订单"), PUBLISH("1002", "订单发布等待接单"), RECEIVING("1003", "已接订单"), DELIVERING("1004",
				"正在配送"), CANCEL("1005", "取消订单"), COMPLETE("1006", "订单完成"), BACK("1007", "退单");

		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		private OrderStatusConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}

	/**
	 * 订单支付状态
	 * 
	 * @author hc-3020-i3
	 *
	 */
	public enum PayStatusConstant {

		PAY_SUCCESS("2000", "支付成功"), PAY_FAILURE("2001", "支付失败"), PAY_PROCESSING("2002", "支付总"), PAY_AWART("2003",
				"待支付订单");

		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		private PayStatusConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}

	}

	/**
	 * 支付类型
	 * @author hc-3020-i3
	 *
	 */
	public enum PayTypeConstant {

		WX("4001", "微信支付"), ZFB("4002", "支付宝支付");

		private String code;
		private String message;

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		private PayTypeConstant(String code, String message) {
			this.code = code;
			this.message = message;
		}
	}
}
