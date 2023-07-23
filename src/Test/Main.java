package Test;

import util.SpringMVC;

public class Main {

	static {
		String path = Main.class.getResource("").getPath();
        String packageName = Main.class.getPackage().getName();
        String scannerPath = path.replaceAll(packageName, "Controller");
        try {
			SpringMVC.springmvc(scannerPath);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SpringMVC.exec("test", "index");
		SpringMVC.exec("test", "index1");
		SpringMVC.exec("test2", "index2");
	}

}
