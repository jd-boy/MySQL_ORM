package mapper;

import annotation.Mapper;
import annotation.Param;
import annotation.Select;

@Mapper
public interface MemberDAO {
	
	@Select({"fjkd"})
	public void f(@Param("a") int a, @Param("s") String s);
}
