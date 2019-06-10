package annotation.handlerImpl;

import java.lang.reflect.Method;

import annotation.handler.AnnotationHandler;
import util.GenerateSqlUtil;

public class SelectHandlerImpl implements AnnotationHandler {

	@Override
	public String getResult(Method method, Object[] args) {
		new GenerateSqlUtil(method, args).getValues();
		return null;
	}

}
