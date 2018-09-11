package org.slazyframework.utils;

import java.beans.BeanInfo;  
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  

public class BeanUtils {
	
	public static void copyBeans(Object source, Object to) throws Exception {    
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),java.lang.Object.class);    
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();    
    
        BeanInfo destBean = Introspector.getBeanInfo(to.getClass(),java.lang.Object.class);    
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();    
    
        try {    
            for (int i = 0; i < sourceProperty.length; i++) {    
    
                for (int j = 0; j < destProperty.length; j++) {    
    
                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {    
                        destProperty[j].getWriteMethod().invoke(to,sourceProperty[i].getReadMethod().invoke(source));    
                        break;    
                    }    
                }    
            }    
        } catch (Exception e) {    
            throw new Exception("转换异常" + e.getMessage());    
        }    
    }    
}
