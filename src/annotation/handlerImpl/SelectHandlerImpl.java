package annotation.handlerImpl;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import annotation.handler.AnnotationHandler;
import util.DBConnectUtil;
import util.GenerateSqlUtil;

public class SelectHandlerImpl implements AnnotationHandler {
	
	private DBConnectUtil conn = new DBConnectUtil();
	
	private Connection con = conn.getConnection();
	private PreparedStatement pre = null;
	private ResultSet result = null;
	
	@Override
	public Object getResult(Method method, Object[] args) {
		
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		String sql = generateSqlUtil.getSpl();
		
		try {
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			
			ResultSetMetaData rsmd = result.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i));
			}
			
			if(result.next()) {
				
			} else {
				return null;
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
		return null;
	}

}
