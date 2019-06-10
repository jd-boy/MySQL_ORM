package util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateSqlUtil {
	
	private Method method;
	private Object[] args;
	private Map<String, Object> map;
	
	public GenerateSqlUtil(Method method, Object[] args) {
		this.method = method;
		this.args = args;
	}
	
	public StringBuffer getValue(Class<?> clazz, Object arg) {
		
		if(clazz.equals(Integer.class)) {
			
		}
		
		return null;
	}

	public String getValues() {
		
		if(method.getParameterCount() == 0) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		Parameter[] parameter = method.getParameters();
		for(int i = 0; i < parameter.length; i++) {
			
			if(parameter[i].getAnnotation(annotation.Param.class) != null) {
				if(ModelFieldUtil.isPrimitive(parameter[i].getParameterizedType())) {
					map.put(parameter[i].getAnnotation(annotation.Param.class).value(), args[i]);
				} else {
					Map<String, Object> m = ModelFieldUtil.getAllFieldValue(args);
					for(Entry<String, Object> entry : m.entrySet()) {
						if(!map.containsKey(entry.getKey())) {
							map.put(entry.getKey(), entry.getValue());
						}
					}
				}
			}
			//System.out.println(parameter[i].getAnnotation(annotation.Param.class).value() + ":" + args[i]);
		}
			
		return null;
	}

	public String getSpl() {
		// TODO Auto-generated method stub
		return null;
	}
}
