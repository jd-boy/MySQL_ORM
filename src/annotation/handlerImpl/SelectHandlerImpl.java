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

import annotation.handler.AnnotationHandler;
import util.DBConnectUtil;
import util.GenerateSqlUtil;

public class SelectHandlerImpl implements AnnotationHandler {
	
	private DBConnectUtil conn = new DBConnectUtil();
	private Connection con = conn.getConnection();
	private PreparedStatement pre = null;
	private ResultSet result = null;
	
	private String sql = null;
	private Map<String, Object> values = null;
	
	@Override
	public Object getResult(Method method, Object[] args) {
		
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		this.sql = generateSqlUtil.getSpl();
		this.values = generateSqlUtil.getValues();
		
		Type type = method.getReturnType();
		
		System.out.println(new Date().toString());
		
		
		return null;
	}
	
	private int integerType() {
		
		try {
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			
			ResultSetMetaData rsmd = result.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i));
			}
			
			if(result.next()) {
				
			} else {
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pre.close();
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

}
