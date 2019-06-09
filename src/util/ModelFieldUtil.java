package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ModelFieldUtil {
	
	public static Map<String, Object> getAllFieldValue(Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		
		
		for(Field f : fields) {
			try {
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
