package util;

import java.awt.List;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

public class TypeUtil {
	
	public static int TYPE_String = 0;
	public static int TYPE_int = 1;
	public static int TYPE_Integer = 2;
	public static int TYPE_double = 3;
	public static int TYPE_Double = 4;
	public static int TYPE_long = 5;
	public static int TYPE_Long = 6;
	public static int TYPE_short = 7;
	public static int TYPE_Short = 8;
	public static int TYPE_float = 9;
	public static int TYPE_Float = 10;
	public static int TYPE_BigDecimal = 11;
	public static int TYPE_BASE = 12;
	public static int TYPE_Date = 13;
	public static int TYPE_List = 14;
	public static int TYPE_OTHER = 15;
	
	public static int isType(Type type) {
		if(type.equals(String.class)) {
			return TYPE_String;
		}
		if(type.equals(int.class)) {
			return TYPE_int;
		}
		if(type.equals(Integer.class)) {
			return TYPE_Integer;
		}
		if(type.equals(double.class)) {
			return TYPE_double;
		}
		if(type.equals(Double.class)) {
			return TYPE_Double;
		}
		if(type.equals(long.class)) {
			return TYPE_long;
		}
		if(type.equals(Long.class)) {
			return TYPE_Long;
		}
		if(type.equals(short.class)) {
			return TYPE_short;
		}
		if(type.equals(Short.class)) {
			return TYPE_Short;
		}
		if(type.equals(float.class)) {
			return TYPE_float;
		}
		if(type.equals(Float.class)) {
			return TYPE_Float;
		}
		if(type.equals(BigDecimal.class)) {
			return TYPE_BigDecimal;
		}
		if(type.equals(Date.class)) {
			return TYPE_Date;
		}
		if(type.equals(List.class)) {
			return TYPE_List;
		}
		return TYPE_OTHER;
	}
	
}
