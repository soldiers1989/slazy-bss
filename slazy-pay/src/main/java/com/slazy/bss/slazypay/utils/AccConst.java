package com.slazy.bss.slazypay.utils;

/**
 * 
 * @Description: 系统常量
 * @author:	xianzhiqiang
 * @date:	2017年6月29日 下午3:03:41
 */
public class AccConst {
	
	//子账户类型
	public static final String SUB_ACC_TYPE_CASH = "0";//现金子账户
	public static final String SUB_ACC_TYPE_BONUS = "1";//红包子账户
	public static final String SUB_ACC_TYPE_BANK = "5";//银行卡子账户
	public static final String SUB_ACC_TYPE_FREEZE = "8";//普通冻结子账户
	public static final String SUB_ACC_TYPE_TENDER = "10";//投标冻结子账户
	public static final String SUB_ACC_TYPE_WITHDRAW = "11";//提现冻结子账户
	public static final String SUB_ACC_TYPE_REPAYMENT = "12";//还款冻结子账户
	public static final String SUB_ACC_TYPE_LEND = "13";//出借冻结子账户	
	public static final String SUB_ACC_TYPE_REPAYMENT_PRE = "15";//还款预处理子账户
	public static final String SUB_ACC_TYPE_ZQRG = "13";//债权认购冻结子账户	
	public static final String SUB_ACC_TYPE_WX = "16";//微信子账户
	public static final String SUB_ACC_TYPE_ZFB = "17";//支付宝子账户
	//账务方向
	public static final String ACC_DIR_TYPE_OUT = "0";//出账
	public static final String ACC_DIR_TYPE_IN = "1";//入账
	
	//交易状态 
	public static final String TRADE_STATE_SUCCESS = "1";//交易成功
	public static final String TRADE_STATE_FAILED = "0";//交易失败
	
	//可用状态
	public static final String STATE_INVALID="0";//无效
	public static final String STATE_VALID="1";//有效

	//激活状态
	public static final String ACTIVAT_INVALID="0";//未激活
	public static final String ACTIVAT_VALID="1";//已激活
	
	//系统标识
    public static final String SYS_ACC = "ACCOUNT";//账务账户
  	public final static String SYS_HHR="HHR";//恒慧融

    //状态码
    public static final String RET_CODE_SUCCESS_ZERO="0000";
    public static final String RET_CODE_FAILED_ZERO="3000";
    public static final String RET_CODE_FAILED_ONE="3001";
    public static final String RET_CODE_FAILED_TWO="3002";
    public static final String RET_CODE_FAILED_THREE="3003";
    public static final String RET_CODE_FAILED_FORE="3004";
    public static final String RET_CODE_FAILED_FIVE="3005";//交易订单号已存在
    public static final String RET_CODE_FAILED_SIX="4000";//部分成功
    public static final String RET_CODE_FAILED_A="3027";//参数验证失败
    public static final String RET_CODE_FAILED_C="4001";//账户余额不足
    public static final String RET_CODE_FAILED_D="4002";//主账户已停用
    public static final String RET_CODE_FAILED_E="4003";//子账户已停用
    public static final String RET_CODE_FAILED_F="4004";//余额被恶意篡改
    public static final String RET_CODE_FAILED_G="4005";//该证件号已开过户
    public static final String RET_CODE_FAILED_H="4006";//主账户不存在
    public static final String RET_CODE_FAILED_I="4007";//子账户不存在
    public static final String RET_CODE_FAILED_J="4008";//账户未绑定银行卡
    public static final String RET_CODE_FAILED_K="4009";//绑定卡不一致 
    public static final String RET_CODE_FAILED_L="4010";//金额不一致
    public static final String RET_CODE_FAILED_M="4011";//已绑定过银行卡
    public static final String RET_CODE_FAILED_N="4012";//批次号不存在
    public static final String RET_CODE_FAILED_O="4013";//数据不存在
    public static final String RET_CODE_FAILED_P="4014";//新换手机号与已绑手机号一致

    //货币单位
    public static final String CURRENCY_UNIT_RMB ="1";//人民币
  
    //业务类型
    public static final String BUSI_TYPE_RECHARGE = "0"; //充值 ： 哈行充值
    public static final String BUSI_TYPE_WITHDRAW = "1";//提现 ：正常提现
    public static final String BUSI_TYPE_CREDIT = "13";//放款
    public static final String BUSI_TYPE_CHECKBALANCE = "2";//调账
    public static final String BUSI_TYPE_CASH_BACK = "34";//返现
    public static final String BUSI_TYPE_CREDITOR_TRANSFER = "7";//债权转让
    public static final String BUSI_TYPE_REPAYMENT = "4";//还款
    public static final String BUSI_TYPE_RISK_COMPENSATORY = "29";//风险金代偿
    public static final String BUSI_TYPE_LATE_PAYMENT = "30";//逾期还款
    public static final String BUSI_TYPE_SETTLEMENT = "6";//提前结清
    public static final String BUSI_TYPE_FUND_UNFROZEN = "22";//资金解冻
    public static final String BUSI_TYPE_FUND_UNTENDER = "41";//投标解冻
    public static final String BUSI_TYPE_FUND_UNWITHDRAW = "42";//提现解冻
    public static final String BUSI_TYPE_FUND_UNREPAYMENT = "44";//还款解冻
    public static final String BUSI_TYPE_FUND_UNLEND = "52";//出借解冻
    
    public static final String BUSI_TYPE_FUND_TENDER_FROZEN = "39";//投标冻结
    public static final String BUSI_TYPE_FUND_WITHDRAW_FROZEN = "40";//提现冻结
    public static final String BUSI_TYPE_FUND_GENERAL_FROZEN = "10";//资金冻结
    public static final String BUSI_TYPE_FUND_REPAYMENT_FROZEN = "43";//还款冻结
    public static final String BUSI_TYPE_FUND_LEND_FROZEN = "51";//出借冻结
    public static final String BUSI_TYPE_FUND_FXJDC = "36";//收益补偿
    public static final String BUSI_TYPE_RISK_FXJCH = "37";//风险金偿还
    public static final String BUSI_TYPE_REDPACKET = "45";//现金红包
    public static final String BUSI_TYPE_HRBB_DAIKOU = "46";//哈行代扣
    public static final String BUSI_TYPE_GATEWAY_RECHARGE = "47";//网关充值
    public static final String BUSI_TYPE_SPEEDY_RECHARGE = "48";//快捷充值
    public static final String BUSI_TYPE_DAIKOU_RECHARGE = "49";//代扣充值
    public static final String BUSI_TYPE_DAIFU_WITHDRAW = "50";//代付（提现）
    public static final String BUSI_TYPE_ZQRG = "53";//债权认购
    public static final String BUSI_TYPE_REPAY_TARGET = "54";//标的还款
    public static final String BUSI_TYPE_COMPANY_CHARGE = "55";//收取服务费【公司收费】
    public static final String BUSI_TYPE_PAY_WX = "16";//微信支付
    public static final String BUSI_TYPE_PAY_ZFB = "17";//支付宝支付
    public static final String BUSI_TYPE_PAY_FINISH = "18";//订单完成
    public static final String BUSI_TYPE_PAY_CANCEL = "19";//订单取消退款
    
    //资金类型
    public static final String CAPITAL_TYPE_FREEZE = "0";//冻结
    public static final String CAPITAL_TYPE_TRANSFER = "1";//转账
    public static final String CAPITAL_TYPE_UNFREEZE = "2";//解冻
    public static final String CAPITAL_TYPE_RECHARGE = "3";//充值
    public static final String CAPITAL_TYPE_MANUALCHECK = "5";//手动调账
    public static final String CAPITAL_TYPE_WITHDRAW = "4";//提现
    public static final String CAPITAL_TYPE_PAY = "6";//支付
  
    //资金名称
    public static final String CAPITAL_NAME_DZ_FREEZE = "0";//到账资金
    public static final String CAPITAL_NAME_SXF_FREEZE = "1";//手续费收取
    public static final String CAPITAL_NAME_DJ_FREEZE = "2";//冻结资金
    public static final String CAPITAL_NAME_JD_FREEZE = "3";//解冻资金
    public static final String CAPITAL_NAME_FFHB_FREEZE = "4";//发放红包资金 
    public static final String CAPITAL_NAME_HBFK_FREEZE = "5";//红包放款资金
    public static final String CAPITAL_NAME_SCCJFK_FREEZE = "6";//首次出借放款资金
    public static final String CAPITAL_NAME_FTFK_FREEZE = "7";//复投放款资金
    public static final String CAPITAL_NAME_KTX_FREEZE = "8";//可提现资金
    public static final String CAPITAL_NAME_LTSQFWF_FREEZE = "9";//利通收取服务费资金
    public static final String CAPITAL_NAME_DSSQFWF_FREEZE = "10";//德盛收取服务费资金
    public static final String CAPITAL_NAME_HCSQFWF_FREEZE = "11";//汇财收取服务费资金
    public static final String CAPITAL_NAME_HCHSQFWF_FREEZE = "12";//惠诚收取服务费资金
    public static final String CAPITAL_NAME_DSBXF_FREEZE = "13";//代收保险费资金
    public static final String CAPITAL_NAME_FS_FREEZE = "14";//返现资金
    public static final String CAPITAL_NAME_DKSDS_FREEZE = "15";//代扣所得税资金
    public static final String CAPITAL_NAME_SQFWF_FREEZE = "16";//收取服务费资金
    public static final String CAPITAL_NAME_HK_FREEZE = "17";//还款资金
    public static final String CAPITAL_NAME_FXJDC_FREEZE = "18";//风险金代偿资金
    public static final String CAPITAL_NAME_CHWYJ_FREEZE = "19";//偿还违约金资金
    public static final String CAPITAL_NAME_CHFX_FREEZE = "20";//偿还罚息资金
    public static final String CAPITAL_NAME_LTTHFWF_FREEZE = "21";//利通退换服务费资金
    public static final String CAPITAL_NAME_DSTHFWF_FREEZE = "22";//德盛退还服务费资金
    public static final String CAPITAL_NAME_HCTHFWF_FREEZE = "23";//汇财退还服务费资金
    public static final String CAPITAL_NAME_HCHTHFWF_FREEZE = "24";//汇诚退还服务费资金
    public static final String CAPITAL_NAME_SYBCJ_FREEZE = "25";//收益补偿资金
    public static final String CAPITAL_NAME_THFWF_FREEZE = "26";//退换服务费资金
    public static final String CAPITAL_NAME_CHFX_CHECKAMOUNT = "27";//调账金额
    public static final String CAPITAL_NAME_CHZJ_FREEZE = "28";//偿还资金
    public static final String CAPITAL_NAME_ZQZR_FREEZE = "29";//债权转让
    public static final String CAPITAL_NAME_FKZJ_FREEZE = "30";//放款资金
    public static final String CAPITAL_NAME_CHFXJZJ_FREEZE = "31";//偿还风险金资金
    public static final String CAPITAL_NAME_RECHARGE_FREEZE = "32";//充值手续费资金
    public static final String CAPITAL_NAME_WITHDRAW_FREEZE = "33";//提现手续费资金
    public static final String CAPITAL_NAME_SYZYZJ_FREEZE = "34";//使用自有资金金额
    public static final String CAPITAL_NAME_ZRZJ_FREEZE = "35";//转让金额
    public static final String CAPITAL_NAME_ZRFEEL_FREEZE = "36";//转让服务费金额
    public static final String CAPITAL_NAME_CXJE_FREEZE = "37";//撤销金额
    public static final String CAPITAL_NAME_SIGNOUT_FREEZE = "38";//退出服务费金额
    public static final String CAPITAL_NAME_OFFSETTING_FREEZE = "39";//冲正金额
    public static final String CAPITAL_NAME_CANCEL_FREEZE = "40";//结清核销金额
    public static final String CAPITAL_NAME_NOCANCEL_FREEZE = "41";//非结清核销金额
    public static final String CAPITAL_NAME_OFFSHEET_FREEZE = "42";//表外金额
    public static final String CAPITAL_NAME_TENDER_FREEZE = "43";//投标金额
    public static final String CAPITAL_NAME_RECEPT_FREEZE = "44";//承接金额
    public static final String CAPITAL_NAME_ADVANCE_FREEZE = "45";//垫付金额
    public static final String CAPITAL_NAME_PRE_AMT = "47";//预处理金额
    public static final String CAPITAL_NAME_HBCJ_AMT = "48";//红包部分承接金额
    public static final String CAPITAL_NAME_DQSH_AMT = "49";//到期赎回服务费金额
    public static final String CAPITAL_NAME_JJSH_AMT = "50";//紧急赎回服务费金额
    public static final String CAPITAL_NAME_REDPACKET_CANCEL_AMT = "51";//红包资金撤销金额
    public static final String CAPITAL_NAME_LOCAL_FREEZE = "52";//自有资金申请金额
//    public static final String CAPITAL_NAME_COMPANY_CHARGE = "53";//收取服务费金额【公司收费】
    public static final String CAPITAL_NAME_SETTLEMENT_FREEZE = "54";//结清服务费金额
    public static final String CAPITAL_NAME_PAY_RELEASE = "55";//发布订单支付
    public static final String CAPITAL_NAME_PAY_FINISH = "55";//订单完成支付
    
    //虚拟子账户编号
    public static final String SUB_BIZ_ID_VIRTUAL = "9999999999999999";
    //提现冻结户
    public static final String SUB_BIZ_ID_WITHDRAW = "666666";
    
    //客户方向类型
    public static final String ACC_TYPE_NO_RIVAL = "0";//本手方
    public static final String ACC_TYPE_YES_RIVAL = "1";//对手方
    
    //银行卡绑定状态
    public static final String BANK_BIND_TYPE_BIND = "1";//绑卡
    public static final String BANK_BIND_TYPE_CHANGE = "2";//换卡
    public static final String BANK_BIND_TYPE_UNDO = "3";//解绑
    
    //公司账户编码(页面配置公司账户编码时：下面的常量+_业务系统标识)
    public static final String COMPANY_ACCOUNT = "/ACC/COMPANY_";//公司账户根路径
    public static final String COMPANY_SERVICE_ACCOUNT = "/FOUR_";//四大服务费账户根路径
    public static final String COMPANY_SERVICE_ACCOUNT_LITONG = "/LITONG_";//利通服务费
    public static final String COMPANY_SERVICE_ACCOUNT_HUICHENG = "/HUICHENG_";//惠诚服务费
    public static final String COMPANY_SERVICE_ACCOUNT_HUICAI = "/HUICAI_";//汇财服务费
    public static final String COMPANY_SERVICE_ACCOUNT_DESHENG = "/DESHENG_";//德胜服务费
    
    public static final String COMPANY_SERVICE_ACCOUNT_SAFE = "/SAFE_";//保险费
    public static final String COMPANY_ACCOUNT_PROMOTION = "/PROMOTION_";//运营推广
    public static final String COMPANY_ACCOUNT_RISK = "/RISK_";//风险金
    public static final String COMPANY_ACCOUNT_BREACH = "/BREACH_";//违约金
    public static final String COMPANY_ACCOUNT_PENALTY = "/PENALTY_";//罚息
    public static final String COMPANY_ACCOUNT_CSSERVICE = "/CS_";//催收服务费
    public static final String COMPANY_ACCOUNT_ZQZRSERVICE = "/ZQZR_";//债权转让服务费（到期赎回服务费）
    public static final String COMPANY_SERVICE_ACCOUNT_RECHARGE = "/RECHARGE_";//充值手续费
    public static final String COMPANY_SERVICE_ACCOUNT_WITHDRAW = "/WITHDRAW_";//提现手续费
    public static final String COMPANY_SERVICE_ACCOUNT_SIGNOUT = "/SIGNOUT_";//退出服务费（紧急赎回服务费）
    public static final String COMPANY_SERVICE_ACCOUNT_INCOMETAX = "/INCOMETAX_";//代扣返现所得税
	/* 新增账户 */
    public static final String COMPANY_ACCOUNT_REPELFEE= "/REPELFEE_";//结清退费账户
    public static final String COMPANY_ACCOUNT_PREMIUMQUALITY = "/PREMIUMQUALITY_";//质保费账户
    public static final String COMPANY_ACCOUNT_IMPACT = "/IMPACT_";//冲正账户
    public static final String COMPANY_ACCOUNT_SERVICE = "/SERVICE_";//服务费账户
    public static final String COMPANY_ACCOUNT_PROCEDURE = "/PROCEDURE_";//手续费账户
    public static final String COMPANY_ACCOUNT_ADVANCE = "/ADVANCE_";//垫付回款账户
    public static final String COMPANY_ACCOUNT_SETTLEMENT = "/SETTLEMENT_";//提前结清服务费账户
    
    //服务费标识
    public static final String  COMPANY_SERVICE_FLAG_GET = "0";//服务费收取
    public static final String  COMPANY_SERVICE_FLAG_BACK = "1";//服务费退还
    
    //客户类型
    public static final String CUSTOMER_TYPE_PERSONAL = "0";//个人账户
    public static final String CUSTOMER_TYPE_COMPANY = "1";//公司账户
    
    //客户身份类型
    public static final String CUSTOMER_ID_TYPE_LENDER = "1";//出借人
    public static final String CUSTOMER_ID_TYPE_BORROWER = "2";//借款人
    
    //生成SN关键字
    public static final String ACCOUNT_NO_SN = "ACCOUNT_NO_SN";//账户编号
    public static final String BIZ_ID_SN = "BIZ_ID_SN";//订单号
    
    //生成SN前缀
    public static final String ACCOUNT_NO_SN_PREFIX = "B_";//公司账户编号
    public static final String ACCOUNT_NO_SN_C_PREFIX = "C_";//账户编号
    public static final String BIZ_ID_SN_PREFIX = "F_";//订单号
    
    //生成SN长度
    public static final int ACCOUNT_NO_SN_LEN = 8;//账户编号
    public static final int BIZ_ID_SN_LEN = 10;//订单号
    
    //金额零
    public static final String AMOUNT_ZERO = "0.00";
    
    //调账审核状态
    public static final String AUDIT_TYPE_NONE = "0";//未审批
    public static final String AUDIT_TYPE_SUCCESS = "1";//审批通过
    public static final String AUDIT_TYPE_REFUSE = "2";//审批拒绝
    public static final String AUDIT_TYPE_FAIL = "3";//审批失败
    
    //=============================邮件开始=========================
    public static final String MAIL_DEST_ADDR = "10.150.20.86";//调用的目标地址
    public static final String MAIL_SYSTEM_SIGN = "ACCOUNTING";//系统标识
    public static final String MAIL_SIGN_INFO = "0000";//签名信息,默认填写0000即可
    public static final String MAIL_DEST_SYSTEM_SIGN = "INFOSERVE";//要调用的目标系统标识
    public static final String MAIL_DEST_INTERFACE = "InfoPub";//目标系统接口名称（开户接口）
    public static final String TZSQ_MAIL = "TZSQMail";
    public static final String TZSH_MAIL = "TZSHMail";
    //=============================邮件结束=========================
    
    //恒丰存管
  	//上传类型 1:全量
  	public static final String UPLOAD_TYPE = "1";
  	public static final String FILE_ENCODING_UTF8 = "UTF-8";
  	public static final String TIMER_PATH = "/ACC/TIMER/";	
  	public static final String USER_INFO = "USER_INFO";	
  	public static final String BANK_INFO = "BANK_INFO";	
  	public static final String BALANCE = "BALANCE";	
  	public static final String STATISTICS = "STATISTICS";	
  	
  	//数据迁移
  	public final static String SYS_BIZ_SYSTEM="HHR";//恒慧融
    public static final int BATCH_COUNT = 500;//数据迁移批量插入限定值
    public static final int THREAD_MAX = 10;//线程池大小
    public static final String FILE_ENCODING_GBK = "GBK";
    
    //公司账户
    public final static String COMPANY_ACCOUNT_PROVISION_ACC="C_0000001";//公司备份金账户
    public final static String COMPANY_ACCOUNT_SERVICE_ACC="C_0000002";//公司服务费账户
    public final static String COMPANY_ACCOUNT_COUPON_ACC="C_0000003";//公司优惠账户
    public final static String COMPANY_ACCOUNT_POUNDAGE_OUT_ACC="C_0000004";//公司手续费账户
    public final static String COMPANY_ACCOUNT_POUNDAGE_IN_ACC="C_0000005";//公司收取手续费账户
    //费用收取比例
    public final static String COMPANY_ACCOUNT_SERVICE_PROPORTION="0.1";//公司服务费收取比例
    public final static String COMPANY_ACCOUNT_POUNDAGE_PROPORTION="0.1";//公司手续费收取比例
    //是否收取费用
    public final static String COMPANY_ACCOUNT_SERVICE_OPEN="Y";//公司服务费账户 Y:收取 N：不收
    public final static String COMPANY_ACCOUNT_POUNDAGE_OPEN="Y";//公司手续费账户  Y:收取 N：不收
}
