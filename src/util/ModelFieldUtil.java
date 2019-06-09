package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ModelFieldUtil {
	
	public Map<String, Object> getAllField(Class<?> clazz, Object obj){
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		
		
		for(Field f : fields) {
			//map.put(f.getName(), )
		}
		
		return map;
	}
	
}
