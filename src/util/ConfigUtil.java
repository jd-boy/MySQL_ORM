package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	
	private Properties pro = null;
	private FileReader fileReader = null;
	
	public ConfigUtil() {
		pro = new Properties();
		try {
			fileReader = new FileReader("pro");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("类ConfigUtil：读取配置文件异常");
		}
		
		try {
			pro.load(fileReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("类ConfigUtil：加载流异常");
		}
		
		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("类ConfigUtil：关闭流异常");
		}
	}
	
	public String getValue(String key) {
		return pro.getProperty(key);
	}
}
