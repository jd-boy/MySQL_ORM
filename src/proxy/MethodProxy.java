package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import annotation.handlerImpl.DeleteHandlerImpl;
import annotation.handlerImpl.InsertHandlerImpl;
import annotation.handlerImpl.SelectHandlerImpl;
import annotation.handlerImpl.UpdateHandlerImpl;

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
		
		Object result = null;
		
		if(method.getAnnotation(annotation.Select.class) != null) {
			SelectHandlerImpl selectHandlerImpl = new SelectHandlerImpl();
			result = selectHandlerImpl.getResult(method, args);
		} else if(method.getAnnotation(annotation.Insert.class) != null) {
			InsertHandlerImpl insertHandlerImpl = new InsertHandlerImpl();
			result = insertHandlerImpl.getResult(method, args);
		} else if(method.getAnnotation(annotation.Update.class) != null) {
			UpdateHandlerImpl updateHandlerImpl = new UpdateHandlerImpl();
			result = updateHandlerImpl.getResult(method, args);
		} else if(method.getAnnotation(annotation.Delete.class) != null) {
			DeleteHandlerImpl deleteHandlerImpl = new DeleteHandlerImpl();
			result = deleteHandlerImpl.getResult(method, args);
		}
		
		return result;
	}

}
