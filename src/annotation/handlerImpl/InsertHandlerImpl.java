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

public class InsertHandlerImpl implements AnnotationHandler {
	
	private DBConnectUtil conn = new DBConnectUtil();
	private Connection con = conn.getConnection();
	private PreparedStatement pre = null;

	@Override
	public Object getResult(Method method, Object[] args) {
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		String sql = generateSqlUtil.getSpl();
		Map<String, Object> values = generateSqlUtil.getValues();
		System.out.println(sql);
		try {
			pre = con.prepareStatement(sql);
			pre.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pre.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

}
