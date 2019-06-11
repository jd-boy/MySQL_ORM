package mapper;

import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import model.User;

@Mapper
public interface MemberDAO {
	
	@Select({"Select * FROM user WHERE id = #{id}"})
	public void f(@Param("id") int a);
}
