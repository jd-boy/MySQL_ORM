package mapper;

import annotation.Insert;
import annotation.Mapper;
import annotation.Param;
import model.User;

@Mapper
public interface MemberDAO {
	
	@Insert({"INSERT INTO user (id, name, age, date) VALUES (#{id}, #{name}, #{age}, #{date})"})
	public int f(@Param("id") User user);
}
