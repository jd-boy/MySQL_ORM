package annotation.handlerImpl;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import annotation.handler.AnnotationHandler;
import util.DBConnectUtil;
import util.GenerateSqlUtil;

public class SelectHandlerImpl implements AnnotationHandler {

	@Override
	public String getResult(Method method, Object[] args) {
		
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		String sql = generateSqlUtil.getSpl();
		
		DBConnectUtil conn = new DBConnectUtil();
		
		Connection con = conn.getConnection();
		ResultSet result = conn.getResultSet();
		try {
			PreparedStatement pre = con.prepareStatement(sql);
			result = pre.executeQuery();
			if(result.next()) {
				
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return null;
	}

}
