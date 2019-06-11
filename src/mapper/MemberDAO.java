package mapper;

import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import model.User;

@Mapper
public interface MemberDAO {
	
	@Select({"Select * FROM user WHERE a = #{a}, name = #{name}, age = #{age}"})
	public void f(@Param("a") int a, @Param("user") User user);
}
