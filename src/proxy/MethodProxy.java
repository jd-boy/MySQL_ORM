package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodProxy implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(proxy, args);
		} else {
			return run(method, args);
		}
		
	}
	
	private Object run(Method method, Object[] args) {
		
		
		if(method.getAnnotation(annotation.Select.class) != null) {
			
		} else if(method.getAnnotation(annotation.Insert.class) != null) {
			
		} else if(method.getAnnotation(annotation.Update.class) != null) {
			
		} else if(method.getAnnotation(annotation.Delete.class) != null) {
			
		}
		
		return method.getName();
	}

}
