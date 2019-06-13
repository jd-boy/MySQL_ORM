package util;

public class AnnotationUtil {
	
	public static int TYPE_SELECT = 0;
	public static int TYPE_INSERT = 1;
	public static int TYPE_DELETE = 2;
	public static int TYPE_UPDATE = 3;
	public static int TYPE_OTHER = 4;
	
	public static Class<?> isAnnotation(Class<?> calzz) {
		
		if(calzz.equals(annotation.Select.class)) {
			return annotation.Select.class;
		}
		if(calzz.equals(annotation.Insert.class)) {
			return annotation.Insert.class;
		}
		if(calzz.equals(annotation.Delete.class)) {
			return annotation.Delete.class;
		}
		if(calzz.equals(annotation.Update.class)) {
			return annotation.Update.class;
		}
		
		return null;
	}
	
}
