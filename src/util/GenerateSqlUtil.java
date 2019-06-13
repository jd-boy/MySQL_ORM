package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class GenerateSqlUtil {
	
	private Method method;
	private Object[] args;
	
	private String sql = "";
	private Map<Integer, Entry<String, Object>> map = null;
	
	private DBConnectUtil conn = new DBConnectUtil();
	private Connection con = conn.getConnection();
	private PreparedStatement pre = null;
	
	public GenerateSqlUtil(Method method, Object[] args) {
		this.method = method;
		this.args = args;
		
		setSql();
		setValues();
		
		for(Entry<Integer, Entry<String, Object>> entry : map.entrySet()) {
			sql = sql.replaceAll("\\#\\{"+entry.getValue().getKey()+"\\}", "?");
		}
	}
	
	private String[] getSqls() {
		if(method.getAnnotation(annotation.Select.class) != null) {
			return method.getAnnotation(annotation.Select.class).value();
		}
		if(method.getAnnotation(annotation.Insert.class) != null) {
			return method.getAnnotation(annotation.Insert.class).value();
		}
		if(method.getAnnotation(annotation.Delete.class) != null) {
			return method.getAnnotation(annotation.Delete.class).value();
		}
		if(method.getAnnotation(annotation.Update.class) != null) {
			return method.getAnnotation(annotation.Update.class).value();
		}
		
		return null;
	}
	
	private void setSql() {
		
		String[] sqls = getSqls();
		
		for(int i = 0; i < sqls.length; i++) {
			sql += sqls[i];
		}
		if(sql.lastIndexOf(';') == -1) {
			sql += ";";
		}
	}
	
	private void setValues() {
		
		if(method.getParameterCount() == 0) {
			return ;
		}
		
		Parameter[] parameters = method.getParameters();
		map = new TreeMap<>();
		Map<String, Object> m = new HashMap<>();
		
		for(int i = 0; i < parameters.length; i++) {
			
			if(parameters[i].getAnnotation(annotation.Param.class) != null) {
				if(TypeUtil.isType(parameters[i].getParameterizedType()) < TypeUtil.TYPE_BASE) {
					m.put(parameters[i].getAnnotation(annotation.Param.class).value(), args[i]);
				} else {
					Map<String, Object> mm = ModelFieldUtil.getAllFieldValue(args[i]);
					for(Entry<String, Object> entry : mm.entrySet()) {
						if(!m.containsKey(entry.getKey())) {
							m.put(entry.getKey(), entry.getValue());
						}
					}
				}
			} else if(TypeUtil.isType(parameters[i].getParameterizedType()) == TypeUtil.TYPE_OTHER) {
				Map<String, Object> mm = ModelFieldUtil.getAllFieldValue(args[i]);
				for(Entry<String, Object> entry : mm.entrySet()) {
					if(!m.containsKey(entry.getKey())) {
						m.put(entry.getKey(), entry.getValue());
					}
				}
			} else if(parameters.length == 1 && 
					TypeUtil.isType(parameters[i].getParameterizedType()) < TypeUtil.TYPE_BASE) {
				
			}
		}
		
		for(Entry<String, Object> entry : m.entrySet()) {
			map.put(sql.indexOf("#{"+entry.getKey()+"}"), entry);
		}
	}
	
	public Map<Integer, Entry<String, Object>> getValues() {
		return this.map;
	}

	public String getSpl() {
		return sql;
	}
	
	public PreparedStatement getPreparedStatement() {
		
		try {
			pre = con.prepareStatement(sql);
			int i = 1;
			for(Entry<Integer, Entry<String, Object>> entry : map.entrySet()) {
				pre.setObject(i, entry.getValue().getValue());
				i ++;
			}
			return pre;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void closePreparedStatement() {
		try {
			if(con != null) {
				con.close();
			}
			if(pre != null) {
				pre.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
