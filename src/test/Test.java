package test;

import java.lang.reflect.Field;

import mapper.MemberDAO;
import proxy.MethodInvoker;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		MemberDAO m = (MemberDAO) MethodInvoker.getInstance(mapper.MemberDAO.class);
		
		m.f(23);
		
		User u = new User();
		
		for(Field f : u.getClass().getDeclaredFields()) {
			try {
				f.setAccessible(true);
				System.out.println(f.get(u));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}


class User {
	private String name = "12";
	private int age = 321;
}