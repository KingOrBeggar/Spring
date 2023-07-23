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
		
		//����map��key��RequestMapping��vauleֵ��value������
		Map<String, String> map = new HashMap<String, String>();

		for (String className:classNames) {
			Class cl = Class.forName(className);
			System.out.println(cl.getName());
			
			Annotation[] annotations = cl.getAnnotations();
	        for (Annotation annotation : annotations) {
	            System.out.println(annotation);
	        }
	        //�ж�java�ļ��Ƿ���Controller
	        Controller controller = (Controller) cl.getAnnotation(Controller.class);
	        if (controller!=null) {
	        	//�����Controller����������е����з���������ÿһ�������е�ע�⣬�õ����е�RequestMapping������RequestMapping�е�valueֵƥ�䷽����
	        	//ƥ��ɹ���RequestMapping�е�value-���������뵽HashMap�У����ӳ��
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
		//1. ʹ��keySet��������key����ͨ��key��ȡvalue
        for(String key:map.keySet()){  
            //System.out.println(key+" "+map.get(key));
        }
		
	}
}
