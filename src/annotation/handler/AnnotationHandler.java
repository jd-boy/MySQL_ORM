package annotation.handler;

import java.lang.reflect.Method;

public interface AnnotationHandler {
	
	
	/**
	 * 获取方法中所有参数的值
	 */
	public String getResult(Method method, Object[] args);
	
	
}
