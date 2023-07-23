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
	//����mapobj��key��ӵ��Controllerע������RequestMappingֵ��value��ӵ��Controllerע������class��ʵ��
	//��¼ע��valueֵ��Controller���classʵ����ӳ��
	private static Map<String, Object> mapobj = new HashMap<String, Object>();
	//����map��key��Controller���RequestMapping��vauleֵ��
	//value����¼RequestMapping��valueֵ�Ͷ�Ӧ������ӳ��
	private static Map<String, HashMap<String, Method>> map = new HashMap<>();
	
	//ģ���������DispatherServlet��ǰ�˴���������·������ת��������Controller
	 public static void exec(String classPath,String methodPath){
	        if(mapobj.get(classPath)==null){
	            System.out.println("û������� 404");
	    }else {
	        if(map.get(classPath).get(methodPath)==null){
	            System.out.println("û��������� 404");
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
			
			//�۲���������ע�⣬û���ã����е�
			Annotation[] annotations = cl.getAnnotations();
	        for (Annotation annotation : annotations) {
	            System.out.println(annotation);
	        }
	        
	        //�ж�java�ļ��Ƿ���Controller
	        Controller controller = (Controller) cl.getAnnotation(Controller.class);
	        if (controller!=null) {
	        	//�����Controller���ҵ�ע���ڸ����ϵ�RequestMapping�е�valueֵ����ӳ����뵽mapobj����valueֵ���뵽map��key��
	        	RequestMapping mapping = (RequestMapping) cl.getAnnotation(RequestMapping.class);
	        	mapobj.put(mapping.value(), cl.newInstance());
	        	map.put(mapping.value(), new HashMap<String, Method>());
	        	//�������е����з�������ÿһ�������ϵ�RequestMappingע���valueֵ�͸÷������ӳ���
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
		//1. ʹ��keySet��������key����ͨ��key��ȡvalue
        for(String key:map.keySet()){  
            System.out.println(key+" "+map.get(key));
        }
		
	}
}
