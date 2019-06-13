package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ModelFieldUtil {
	
	/**
	 * 为类中指定成员变量赋值（包括私有成员变量）
	 * @author JZ
	 * @param values key为成员变量名，value为成员共变量值
	 * @param clazz 需要映射的class类型
	 * @return Object 动态实现的类
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object setFieldValue(Map<String, Object> values, Type type) {
		
		Class clazz = null;
		
		try {
			clazz = Class.forName(type.getTypeName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object obj = null;
		
		try {
			obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			
			for(Field field : fields) {
				field.setAccessible(true);
				field.set(obj, values.get(field.getName()));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * 返回类中所有成员变量(包括私有成员变量)的值
	 * @author JZ
	 * @param obj
	 * @return Map<String, Object>
	 */
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
	
}
