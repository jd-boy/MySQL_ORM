package util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ModelFieldUtil {
	
	public static Boolean isPrimitive(Type type) {
		if(type.equals(String.class)) {
			return true;
		}
		if(type.equals(int.class)) {
			return true;
		}
		if(type.equals(Integer.class)) {
			return true;
		}
		if(type.equals(double.class)) {
			return true;
		}
		if(type.equals(Double.class)) {
			return true;
		}
		if(type.equals(Date.class)) {
			return true;
		}
		if(type.equals(long.class)) {
			return true;
		}
		if(type.equals(Long.class)) {
			return true;
		}
		if(type.equals(short.class)) {
			return true;
		}
		if(type.equals(Short.class)) {
			return true;
		}
		if(type.equals(float.class)) {
			return true;
		}
		if(type.equals(Float.class)) {
			return true;
		}
		if(type.equals(BigDecimal.class)) {
			return true;
		}
		
		return false;
	}
	
	public static Map<String, Object> getAllFieldValue(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			try {
				f.setAccessible(true);
				map.put(f.getName(), f.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	public void setFieldValue() {
		
	}
	
}
