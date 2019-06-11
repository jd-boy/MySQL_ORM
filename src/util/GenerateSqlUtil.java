package util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateSqlUtil {
	
	private Method method;
	private Object[] args;
	
	public GenerateSqlUtil(Method method, Object[] args) {
		this.method = method;
		this.args = args;
	}
	
	private Map<String, Object> getValues() {
		
		Map<String, Object> map = new HashMap<>();
		
		if(method.getParameterCount() == 0) {
			return null;
		}
		
		Parameter[] parameter = method.getParameters();
		for(int i = 0; i < parameter.length; i++) {
			
			if(parameter[i].getAnnotation(annotation.Param.class) != null) {
				if(ModelFieldUtil.isPrimitive(parameter[i].getParameterizedType())) {
					map.put(parameter[i].getAnnotation(annotation.Param.class).value(), args[i]);
				} else {
					Map<String, Object> m = ModelFieldUtil.getAllFieldValue(args[i]);
					for(Entry<String, Object> entry : m.entrySet()) {
						if(!map.containsKey(entry.getKey())) {
							map.put(entry.getKey(), entry.getValue());
						}
					}
				}
			}
		}
		/*for(Entry<String, Object> e : map.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue());
		}*/
		return map;
	}

	public String getSpl() {
		
		Map<String, Object> map = getValues();
		
		String[] sqls = method.getAnnotation(annotation.Select.class).value();
		
		String sql = "";
		
		for(int i = 0; i < sqls.length; i++) {
			sql += sqls[i];
		}
		
		for(Entry<String, Object> entry : map.entrySet()) {
			sql = sql.replaceAll("\\#\\{"+entry.getKey()+"\\}", entry.getValue().toString());
		}
		sql += ";";
		return sql;
	}
}
