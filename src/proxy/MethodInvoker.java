package proxy;

import java.lang.reflect.Proxy;

public class MethodInvoker {
	
	public static Object getInstance(Class<?> clazz) {
		MethodProxy methodProxy = new MethodProxy();
		
		Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, methodProxy);
		
		return (Object) obj;
	}
	
}
