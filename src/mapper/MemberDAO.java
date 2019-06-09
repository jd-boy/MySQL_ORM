package mapper;

import annotation.Mapper;
import annotation.Select;
import test.Test;

@Mapper
public interface MemberDAO {
	
	@Select({"fjkd"})
	public void f(int a);
}
