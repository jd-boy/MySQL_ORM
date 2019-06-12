package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectUtil {
	
	private ConfigUtil configUtil = null;
	
	private Connection conn = null;
	
	private String url;
	private String user;
	private String password;
	
	public DBConnectUtil() {
		configUtil = new ConfigUtil();
		
		try {
			Class.forName(configUtil.getValue("datasource.driver"));
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.err.println("类DBConnect：MySQL数据库驱动加载失败");
		}
		
		url = configUtil.getValue("datasource.url");
		user = configUtil.getValue("datasource.user");
		password = configUtil.getValue("datasource.password");
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("类DBConnect：数据库连接失败");
		}
	}

	public Connection getConnection() {
		return conn;
	}

}
