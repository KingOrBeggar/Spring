package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import Annotation.Controller;
import Annotation.RequestMapping;

public class SpringMVC {
	//设置mapobj，key：拥有Controller注解的类的RequestMapping值，value：拥有Controller注解的类的class类实例
	//记录注解value值和Controller类的class实例的映射
	private static Map<String, Object> mapobj = new HashMap<String, Object>();
	//设置map，key：Controller类的RequestMapping的vaule值，
	//value：记录RequestMapping的value值和对应方法的映射
	private static Map<String, HashMap<String, Method>> map = new HashMap<>();
	
	//模拟服务器中DispatherServlet对前端传来的请求路径进行转发给各个Controller
	 public static void exec(String classPath,String methodPath){
	        if(mapobj.get(classPath)==null){
	            System.out.println("没有这个类 404");
	    }else {
	        if(map.get(classPath).get(methodPath)==null){
	            System.out.println("没有这个方法 404");
	        }else {
	            try {
	                map.get(classPath).get(methodPath).invoke(mapobj.get(classPath));
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	}
	public static void springmvc(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		path = "D:\\appdata\\ssm-workspace\\Spring\\src\\Controller";
		
		List<String> classNames = GetAllFiles.getAllFiles(path);

		for (String className:classNames) {
			Class cl = Class.forName(className);
			//System.out.println(cl.getName());
			
			//观察该类的所有注解，没鸟用，纯闲的
			Annotation[] annotations = cl.getAnnotations();
	        for (Annotation annotation : annotations) {
	            System.out.println(annotation);
	        }
	        
	        //判断java文件是否是Controller
	        Controller controller = (Controller) cl.getAnnotation(Controller.class);
	        if (controller!=null) {
	        	//如果是Controller，找到注解在该类上的RequestMapping中的value值，将映射加入到mapobj，将value值加入到map的key中
	        	RequestMapping mapping = (RequestMapping) cl.getAnnotation(RequestMapping.class);
	        	mapobj.put(mapping.value(), cl.newInstance());
	        	map.put(mapping.value(), new HashMap<String, Method>());
	        	//遍历其中的所有方法，将每一个方法上的RequestMapping注解的value值和该方法完成映射绑定
	        	Method[] methods = cl.getDeclaredMethods(); 
				for (Method m:methods) {
					Annotation[] annotations_m = m.getAnnotations();
					for (Annotation annotation : annotations_m) {
			            RequestMapping requestMapping = (RequestMapping) m.getAnnotation(RequestMapping.class);
			            map.get(mapping.value()).put(requestMapping.value(), m);
			        }
				}
	        }
	    
		}
		//System.out.println(map.size());
		//1. 使用keySet方法遍历key，再通过key获取value
        for(String key:map.keySet()){  
            System.out.println(key+" "+map.get(key));
        }
		
	}
}
