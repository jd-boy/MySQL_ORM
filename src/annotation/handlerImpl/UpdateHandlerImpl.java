package annotation.handlerImpl;

import java.lang.reflect.Method;
import java.sql.SQLException;

import annotation.handler.AnnotationHandler;
import util.DBConnectUtil;
import util.GenerateSqlUtil;

public class UpdateHandlerImpl implements AnnotationHandler {

	@Override
	public Object getResult(Method method, Object[] args) {
		DBConnectUtil conn = new DBConnectUtil();
		GenerateSqlUtil generateSqlUtil = new GenerateSqlUtil(method, args);
		try {
			return generateSqlUtil.getPreparedStatement().executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			generateSqlUtil.closePreparedStatement();
		}
		
		return -1;
	}

}
