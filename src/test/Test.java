package test;

import mapper.MemberDAO;
import proxy.MethodInvoker;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		MemberDAO m = (MemberDAO) MethodInvoker.getInstance(mapper.MemberDAO.class);
		
		m.f(23, "fd");
		
		
	}
	
}