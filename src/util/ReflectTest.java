package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import Annotation.Controller;
import Annotation.RequestMapping;

public class ReflectTest {
	public static void main(String[] args) throws ClassNotFoundException {
		String path = "D:\\appdata\\ssm-workspace\\Spring\\src\\Controller";
		
		List<String> classNames = GetAllFiles.getAllFiles(path);
		
		//设置map，key：RequestMapping的vaule值；value：方法
		Map<String, String> map = new HashMap<String, String>();

		for (String className:classNames) {
			Class cl = Class.forName(className);
			System.out.println(cl.getName());
			
			Annotation[] annotations = cl.getAnnotations();
	        for (Annotation annotation : annotations) {
	            System.out.println(annotation);
	        }
	        //判断java文件是否是Controller
	        Controller controller = (Controller) cl.getAnnotation(Controller.class);
	        if (controller!=null) {
	        	//如果是Controller，则遍历其中的所有方法，遍历每一个方法中的注解，得到其中的RequestMapping，根据RequestMapping中的value值匹配方法名
	        	//匹配成功后将RequestMapping中的value-方法名加入到HashMap中，完成映射
	        	Method[] methods = cl.getDeclaredMethods(); 
				for (Method m:methods) {
					Annotation[] annotations_m = m.getAnnotations();
					for (Annotation annotation : annotations_m) {
			            //System.out.println(annotation);
			            RequestMapping requestMapping = (RequestMapping) m.getAnnotation(RequestMapping.class);
			           //System.out.println(requestMapping.value());
			            //System.out.println(m.getName());
			            if (requestMapping.value().equals(m.getName()) ) {
			            	map.put(requestMapping.value(), m.getName());
			            }
			        }
				}
	        }
	    
		}
		System.out.println(map.size());
		//1. 使用keySet方法遍历key，再通过key获取value
        for(String key:map.keySet()){  
            //System.out.println(key+" "+map.get(key));
        }
		
	}
}
