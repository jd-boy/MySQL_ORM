package annotation.handlerImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import annotation.handler.AnnotationHandler;
import util.DBConnectUtil;
import util.GenerateSqlUtil;

public class InsertHandlerImpl implements AnnotationHandler {
	
	@Override
	public Object getResult(Method method, Object[] args) {
		int result = -1;
		
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
			
		try {
			result = generateSqlUtil.getPreparedStatement().executeUpdate();
			generateSqlUtil.closePreparedStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return (int) result;
	}
	
}
