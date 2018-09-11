package com.slazy.bss.slazypay.utils;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.log4j.Logger;

//import cfca.sm2rsa.common.PKIException;
//import cfca.util.SignatureUtil2;
//import cfca.util.cipher.lib.JCrypto;
//import cfca.util.cipher.lib.Session;
//import cfca.x509.certificate.X509Cert;
//import cfca.x509.certificate.X509CertValidator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.slazy.bss.slazypay.vo.res.AccountResVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

	
/**
 * 
 * @Description: 工具类
 * @author:	xianzhiqiang
 * @date:	2017年7月3日 下午4:39:36
 */
public class AccUtil {

	private  static Logger log = Logger.getLogger(AccUtil.class);
//	public static Session session = null;
	//获取远程实现类
	
	/**
	 * 大于0,判断
	 * @param num 数值
	 * @return num > 0
	 */
	public static boolean gt_Zero(String num){
		return new BigDecimal(num).compareTo(BigDecimal.ZERO) > 0;
	}
	
	/**
	 * 
	 * @Description: 字符串转BigDecimal
	 * @author:	xianzhiqiang
	 * @date:	2017年7月3日 下午2:13:02
	 */
	public static BigDecimal StrToBigDecimal(String str) throws Exception {
		BigDecimal decimal = BigDecimal.ZERO;
		if (str != null && !"".equals(str)) {
			decimal = new BigDecimal(str);
		}
		return decimal;
	}
	
	/**
	 * 
	 * @Description:BigDecimal转字符串
	 * @author:	xianzhiqiang
	 * @date:	2017年7月6日 下午5:45:01
	 */
	public static String bigDecimal2String(BigDecimal bd){
	    if(bd==null){
	    	bd=new BigDecimal("0");
	    }
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入保留两位小数
	    return bd.toString();
	}
	
	/**
	 * 
	 * @Description: MD5加密
	 * @author:	xianzhiqiang
	 * @date:	2017年7月12日 上午10:45:15
	 */
	public static String string2MD5(String str){
		
	StringBuffer sb = new StringBuffer();
		
	try {
		str = str+AccConst.SYS_ACC;
		byte[] btInput = str.getBytes();
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		for (int i = 0; i < md.length; i++) {
			int val = ((int) md[i]) & 0xff;
			if (val < 16)
				sb.append("0");
			sb.append(Integer.toHexString(val));
	       }
         
	} catch (Exception e) {
		
		log.error("MD5加密异常：",e);
	}
	return sb.toString();
	}
	
	/**
	 * 
	 * @Description: 交易返回结果处理
	 * @author:	xianzhiqiang
	 * @date:	2017年7月12日 下午5:07:00
	 */
	public static ResponseVo returnHandle(Exception e){
		ResponseVo res = new ResponseVo();
		try{
			if(RuntimeException.class.isAssignableFrom(e.getClass())){
				res = JSON.parseObject(e.getMessage(),ResponseVo.class);
				List<AccountResVo> accList = new ArrayList<AccountResVo>();
				JSONArray list = (JSONArray)res.getObj();
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						AccountResVo vo = new AccountResVo();
						vo.setBizId(list.getJSONObject(i).getString("bizId"));
						vo.setRetCode(list.getJSONObject(i).getString("retCode"));
						vo.setRetInfo(list.getJSONObject(i).getString("retInfo"));
						accList.add(vo);
					}		
					res.setObj(accList);
				}
				
			}else{
				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
				res.setRetInfo(e.getMessage());
			}
		}catch(Exception ex){
			log.error("交易返回结果处理异常：",ex);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		return res;
	}
	
	
	 
    /**
     * 
     * 描 述：UKEY验签
     * 作 者：xianzhiqiang
     * @param signData
     * @return err
     */
//    public static Map<String,String> ukeyVerify(String signData){
//    	Map err = new HashMap<String,String>();
//		try {
//			 try {
//				JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
//			} catch (Exception e) {
//				e.printStackTrace();
//				err.put("3001", e.getMessage());//初始化异常！
//				return err;
//			}	
//			 session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
//			 SignatureUtil2 util2 = new SignatureUtil2();
//		        if(util2.p7VerifyMessageAttach(signData.getBytes(), session)){
//		        	log.debug(">>>1 验证P7签名值成功!");
//		            byte mingwen[] = util2.getSourceData();
//		            log.debug(">>>签名原文 = " + new String(mingwen));
//		            
//		            X509Cert userCert = util2.getSignerCert();            
//		            if(X509CertValidator.verifyCertDate(userCert)){
//		            	log.debug(">>>2 证书期限有效: " + userCert.getNotBefore() + "---" + userCert.getNotAfter());
//		            }else{
//		            	log.debug(">>>2 证书过期:  " + userCert.getNotBefore() + "---" + userCert.getNotAfter());
//		                err.put("3001", "证书过期!");
//		                return err;
//		            }
//		            ClassLoader classLoader = AccUtil.class.getClassLoader();     
////			            String trustCertPath = "C:\\Users\\3446\\Desktop\\签名验签工具包2\\签名验签工具包\\04证书链及吊销列表\\ca.cer";
////		            String trustCertPath = CoreConst.SEC_PATH + "/ca.cer";//生产环境去掉/tmp
//		            ISysService dictService = SpringContextUtil.getBean("sysService");
//		            String trustCertDictPath = "/ACC/UKEY_SIGNATURE_PATH/TRUST_CERT_PATH";
//		    		Dict trustCertPathDict = (Dict)dictService.execute(new Parameter(getService(),"findByPath",trustCertDictPath)).getResult();
//		            String trustCertPath = classLoader.getResource(trustCertPathDict.getValue1()).toURI().getPath();
//		            X509CertValidator.updateTrustCertsMap(trustCertPath);
//		            if(X509CertValidator.validateCertSign(userCert)){
//		            	log.debug(">>>3 证书颁发者正确!");
//		            }else{
//		            	log.debug(">>>3 证书颁发者错误!");
//		                err.put("3001", "证书颁发者错误!");
//		                return err;
//		            }
//		           
////			            String clrPath = "C:\\Users\\3446\\Desktop\\签名验签工具包2\\签名验签工具包\\04证书链及吊销列表\\ca.crl";
////		            String clrPath = CoreConst.SEC_PATH + "/ca.crl";
//		            String clrDictPath = "/ACC/UKEY_SIGNATURE_PATH/CLR_PATH";
//		    		Dict clrPathDict = (Dict)dictService.execute(new Parameter(getService(),"findByPath",clrDictPath)).getResult();
//		            String clrPath = classLoader.getResource(clrPathDict.getValue1()).toURI().getPath();
//		            if(X509CertValidator.verifyCertByCRLOutLine(userCert, clrPath)){          	
//		            	log.debug(">>>4 证书为有效证书，未吊销!");
//		            }else{
//		            	log.debug(">>>4 该证书已吊销!"); 
//		                err.put("0000", "该证书已吊销!");//咨询中金人员说可以将已吊销不列入失败结果
//		                return err;
//		            }            
//		            
//		        }else{
//		        	log.debug(">>>1 验证P7签名值失败!");
//		            err.put("3001", "验证P7签名值失败!");
//		            return err;
//		        }  		 
//		} catch (PKIException e) {
//			e.printStackTrace();
//			err.put("3001", e.getMessage());
//			return err;
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//			err.put("3001", e.getMessage());
//			return err;
//		}
//
//		err.put("0000", "验证成功！");
//		return err;
//    }
    
	public static String getService() {
		return "dictService";
	}
	
	/**
	 * 
	 * @Description:发送邮件
	 * @param:address 
	 * @param:tplKey 邮件模版关键字
	 * @param:map邮件内容
	 * @return:MqResponseVo
	 * @throws：Exception
	 * @author:	wangyanan
	 * @date:	2017-10-17 上午11:20:43
	 */
//	public static MqResponseVo sendEmail(String title,String address,String tplKey,Map map) throws Exception {
//		MqRequestVo req = new MqRequestVo();
//		//目标IP地址，开发环境必填，上线之后之后自动失效，不用更改
//		req.setDestAddr(AccConst.MAIL_DEST_ADDR);
//		//操作人,发送邮件是系统，填系统标识
//		req.setOperator(AccConst.MAIL_SYSTEM_SIGN);
//		//唯一消息ID、已系统标示(.)为前缀
//		req.setMsgId(AccConst.MAIL_SYSTEM_SIGN+"."+UUID.randomUUID().toString());
//		//签名信息,默认填写0000即可
//		req.setSignInfo(AccConst.MAIL_SIGN_INFO);
//		//当前系统标识,必须在ESB客户端注册,配置之后取配置信息即可
//		req.setSystemSign(AccConst.MAIL_SYSTEM_SIGN);
//		//要调用的目标系统标识
//		req.setDestSystemSign(AccConst.MAIL_DEST_SYSTEM_SIGN);
//		//目标系统接口名称（开户接口）
//		req.setDestInterface(AccConst.MAIL_DEST_INTERFACE);
//		MailJsonVo vo = new MailJsonVo();
//		vo.setTitle(title);
//		vo.setAddr(address);
//		vo.setBizId(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));
//		vo.setTplKey(tplKey);//邮件模版关键字
//		vo.setSystemSign(AccConst.MAIL_SYSTEM_SIGN);//系统标识
//		vo.setOperator(AccConst.MAIL_SYSTEM_SIGN);
//		vo.setFieldJson(JSON.toJSONString(map));
//		String jsonMsg = JSON.toJSONString(vo);
//		//调用的请求入参,json格式
//		req.setJsonParam(jsonMsg);
//		MqResponseVo response = service.mqSendRtRequest(req);
//		/**响应码状态描述*/
//		//0000(成功),发送或接收失败(1000),接口不存在或未启用(1999)
//		//处理中(2000),3000(失败),部分成功(4000),已撤销(5000)
//		return response;
//	}
	
	/**
	 * 
	 * @Description: 使用 Map按key进行排序
	 * @author:	xianzhiqiang
	 * @date:	2017年9月20日 下午9:05:38
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
	        if (map == null || map.isEmpty()) {
	            return null;
	        }

	        Map<String, Object> sortMap = new TreeMap<String, Object>(
	                new MapKeyComparator());

	        sortMap.putAll(map);

	        return sortMap;
	    }
	
	/**
	 * 
	 * @Description: 根据身份证号获取性别
	 * @author:	xianzhiqiang
	 * @date:	2017年12月8日 下午2:37:34
	 */
	public static String getSex(String idNum){
		if(idNum == null || "".equals(idNum)){
			return "";
		}
		String sex = "";  
		//18位身份证
		if(idNum.length()==18){
			  if (Integer.parseInt(idNum.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别  
		            sex = "1";  
		        } else {  
		            sex = "0";  
		        }  
		}else if(idNum.length()==15){
			//15位身份证
	        String usex = idNum.substring(14, 15);// 用户的性别 
	        if (Integer.parseInt(usex) % 2 == 0) {  
	            sex = "1";  
	        } else {  
	            sex = "0";  
	        }  
		}
        
		return sex;
	}
	
	/* 身份证号、银行卡号、手机号脱敏 */
	public static String transform(String str, int frontLen, int endLen) {
	    int len = str.length()-frontLen-endLen;
	    String star = "";
	    for (int i=0;i<len;i++) {
	    	star += "*";
	    }
	    String prefix = str.substring(0,frontLen);
	    String postfix = str.substring(str.length()-endLen);
	    return prefix + star + postfix;
	}
}

	/**
	 * 
	 * @Description: 比较器类
	 * @author:	xianzhiqiang
	 * @date:	2017年9月20日 下午9:06:09
	 */
	class MapKeyComparator implements Comparator<String>{
	    @Override
	    public int compare(String str1, String str2) {
	        return str1.compareTo(str2);
	    }
	}
	
	
