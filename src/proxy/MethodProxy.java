package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import annotation.handlerImpl.InsertHandlerImpl;
import annotation.handlerImpl.SelectHandlerImpl;

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
			new SelectHandlerImpl().getResult(method, args);
		} else if(method.getAnnotation(annotation.Insert.class) != null) {
			new InsertHandlerImpl().getResult(method, args);
		} else if(method.getAnnotation(annotation.Update.class) != null) {
			
		} else if(method.getAnnotation(annotation.Delete.class) != null) {
			
		}
		
		return method.getName();
	}

}
