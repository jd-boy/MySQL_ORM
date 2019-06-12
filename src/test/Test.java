package test;

import mapper.MemberDAO;
import model.User;
import proxy.MethodInvoker;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		MemberDAO m = (MemberDAO) MethodInvoker.getInstance(mapper.MemberDAO.class);
		
		User u = new User();
		
		m.f(u);
		
		
	}
	
}