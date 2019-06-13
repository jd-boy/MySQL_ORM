package test;

import mapper.MemberDAO;
import model.User;
import proxy.MethodInvoker;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		MemberDAO m = (MemberDAO) MethodInvoker.getInstance(mapper.MemberDAO.class);
		
		User u = new User();
		
		//System.out.println(m.insert(u));
		
		System.out.println(m.delete(5));
		
		/*for(User user : m.select(5)) {
			System.out.println(user.getName());
		}*/
		
	}
	
}