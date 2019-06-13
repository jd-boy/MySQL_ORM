package mapper;

import java.util.LinkedList;
import java.util.List;

import annotation.Insert;
import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import model.User;

@Mapper
public interface MemberDAO {
	
	@Insert({"INSERT INTO user (id, name, age, date) VALUES (#{id}, #{name}, #{age}, #{date})"})
	public int insert(@Param("id") User user);
	
	@Select({"SELECT * FROM user WHERE id = #{id}"})
	public LinkedList<User> select(@Param("id") int id);
}
