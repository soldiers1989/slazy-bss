package com.slazy.bss.slazy.commons.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecretTools {
	
	final static Logger logger = LoggerFactory.getLogger(SecretTools.class);  
	
	static RSAEncryptor rsaEncryptor;
	
	static 
	{
		 try {
			rsaEncryptor = new RSAEncryptor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * 加密方法
     * @param text
     * @return
     * @throws Exception
     */
    public static String encryption(String text) throws Exception 
    {
    	if(logger.isDebugEnabled())
    		logger.debug(String.format("", ""));
    	
		return rsaEncryptor.encryptWithBase64(text);
    }
	
    /**
     * @param text
     * @return
     * @throws Exception
     */
    public static String decryption(String text) throws Exception 
    {
    	return rsaEncryptor.decryptWithBase64(text);
    }
    
    public static void main(String[] args) throws Exception {
    	System.out.println(System.getProperty("user.dir"));
		System.out.println(decryption(encryption("test")));
	}
}
