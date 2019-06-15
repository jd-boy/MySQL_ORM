package annotation.handlerImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import annotation.handler.AnnotationHandler;
import util.GenerateSqlUtil;
import util.ModelFieldUtil;
import util.TypeUtil;

public class SelectHandlerImpl implements AnnotationHandler {
	
	private Method method = null;
	private Object[] args;
	
	private ResultSet result = null;
	private ResultSetMetaData rsmd = null;
	
	private String sql = null;
	private Map<Integer, Entry<String, Object>> values = null;
	
	@Override
	public Object getResult(Method method, Object[] args) {
		
		this.method = method;
		this.args = args;
		
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
	
	private <T> List listType(Class clazz){
		ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
		Type[] types = parameterizedType.getActualTypeArguments();
		List<T> list = null;
		
		try {
			//list = (List<T>) parameterizedType.getClass().getConstructor(null).newInstance(null);
			list = (List<T>) clazz.getConstructor(null).newInstance(null);
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			while(result.next()) {
				
				Map<String, Object> map = new HashMap<>();
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), result.getObject(i));
				}
				
				list.add((T) ModelFieldUtil.setFieldValue(map, types[0]));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(list.size() == 0) {
			return null;
		}
		
		return list;
	}

}
