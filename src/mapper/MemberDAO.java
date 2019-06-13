package mapper;

import java.util.LinkedList;
import java.util.List;

import annotation.Delete;
import annotation.Insert;
import annotation.Mapper;
import annotation.Param;
import annotation.Select;
import annotation.Update;
import model.User;

@Mapper
public interface MemberDAO {
	
	@Insert({"INSERT INTO user (id, name, age, date) VALUES (#{id}, #{name}, #{age}, #{date})"})
	public int insert(User user);
	
	@Select({"SELECT * FROM user WHERE id = #{id}"})
	public LinkedList<User> select(@Param("id") int id);
	
	@Update({"UPDATE user set age = #{age} WHERE id = #{id}"})
	public int update(@Param("id") int id);
	
	@Delete({"DELETE FROM user WHERE id = #{id}"})
	public int delete(@Param("id") int id);
}
