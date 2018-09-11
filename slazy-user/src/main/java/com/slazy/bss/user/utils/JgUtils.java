package com.slazy.bss.user.utils;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.RegisterPayload;
import cn.jmessage.api.common.model.UserPayload;
import cn.jmessage.api.user.UserClient;

/**
 * 极光工具类
 */
public class JgUtils {

	final static Logger logger = LoggerFactory.getLogger(JgUtils.class);
    private static final String APP_KEY = "4d39f1482163cf115921b7e7";
    private static final String MASTER_SECRET = "41e9958035169dab824bd8ef";
    private static UserClient userClient = null;
    
    static {
    	try {
    		userClient = new UserClient(APP_KEY, MASTER_SECRET);
    	}catch(Exception e) {
    		logger.error("创建极光客户端异常：",e);
    	}
    	
    }
    /**
     * 极光注册
     */
    public static void JgRegisterUser(String username,String password) throws Exception{

    		logger.info("注册极光参数：username="+username+" password="+password);
    		
            List<RegisterInfo> users = new ArrayList<RegisterInfo>();
            RegisterInfo user = RegisterInfo.newBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .build();


            users.add(user);
            RegisterInfo[] regUsers = new RegisterInfo[users.size()];

            RegisterPayload payload = RegisterPayload.newBuilder()
                    .addUsers(users.toArray(regUsers)).build();

            ResponseWrapper res = userClient.registerUsers(payload);
            logger.info("注册极光返回结果："+res);
            
            logger.info("注册极光成功！");
    }
    
    /**
     * 删除极光用户
     */
    public static void JgDelUser(String username) throws Exception{
    	logger.info("删除极光用户参数：username="+username);
    	
    	 ResponseWrapper res = userClient.deleteUser("junit_test_userssss1");
    	 
    	 logger.info("删除极光用户返回结果："+res);
         
         logger.info("删除极光用户成功！");
    }
    
    /**
     * 设置极光用户昵称
     */
    public static void JgUpdateUser(String username,String nickName) throws Exception{
    	logger.info("设置极光用户昵称参数：username="+username+" nickName="+nickName);
    	 UserPayload payload = UserPayload.newBuilder()
                 .setNickname(nickName)
                 .build();

         ResponseWrapper res = userClient.updateUserInfo(username, payload);
         logger.info("设置极光用户昵称返回结果："+res);
         
         logger.info("设置极光用户昵称成功！");
    }
}
