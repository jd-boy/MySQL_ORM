package util;

import java.lang.reflect.Method;

public class GenerateSqlUtil {
	
	private Method method;
	private Object[] args;
	
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
		
		StringBuffer sb = new StringBuffer();
		
		Class<?>[] clazzs = null;
		
		if(method.getParameterCount() == 0) {
			return null;
		}
		
		if(args.length == 1) {
			
			clazzs = method.getParameterTypes();
			
			
		}
		
		return null;
	}

	public String getSpl() {
		// TODO Auto-generated method stub
		return null;
	}
}
