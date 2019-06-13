package annotation.handlerImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import annotation.handler.AnnotationHandler;
import util.GenerateSqlUtil;
import util.TypeUtil;

public class SelectHandlerImpl implements AnnotationHandler {
	
	private ResultSet result = null;
	private ResultSetMetaData rsmd = null;
	
	private String sql = null;
	private Map<Integer, Entry<String, Object>> values = null;
	
	@Override
	public Object getResult(Method method, Object[] args) {
		
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		
		try {
			result = generateSqlUtil.getPreparedStatement().executeQuery();
			rsmd = result.getMetaData();
			
			
			if(TypeUtil.isType(method.getReturnType()) < TypeUtil.TYPE_BASE) {
				return primitiveType();
			} else if(List.class.isAssignableFrom(method.getReturnType())) {
				return listType(method.getReturnType());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			generateSqlUtil.closePreparedStatement();
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	
	private Object primitiveType(){
		try {
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private List<?> listType(Class<?> clazz){
		List<?> list = null;
				
		return list;
	}

}
