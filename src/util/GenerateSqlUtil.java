package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
		
		if(method.getParameterCount() == 0) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		
		Annotation[][] annotations = null;
		
		Parameter[] p = method.getParameters();
		for(Parameter pp : p) {
			System.out.println(pp.getAnnotation(annotation.Param.class).value());
		}
		/*annotations = method.getParameterAnnotations();
		System.out.println(annotations[0][0].annotationType().getName());
		for(Annotation[] ann : annotations) {
			for(Annotation a : ann) {
					System.out.println(a.annotationType());
			}
		}*/
			
		return null;
	}

	public String getSpl() {
		// TODO Auto-generated method stub
		return null;
	}
}
