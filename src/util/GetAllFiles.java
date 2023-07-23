package util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class GetAllFiles {
	static int count = 0;
	public static List<String> getAllFiles(String path) {
		System.out.println(path);
		List<String> qualifiedPaths = new ArrayList<String>();
		String fileType = ".java";// ָ���ļ�����
        File file = new File(path);
        
        long start = System.currentTimeMillis();
        listAllFiles(file, fileType, qualifiedPaths);
        long end = System.currentTimeMillis();
        System.out.println("�����ѣ�" + (end - start) + "����");
        System.out.println("��" + count + "���ļ�");
        return qualifiedPaths;
	}
	public static void listAllFiles(File file, String fileType, List<String> qualifiedPaths){
        if (file.isFile()){
            count++;
            System.out.println(file);

            String path = file.getPath().replace('\\', '.');
            int begin = path.indexOf("Controller");
            int end = path.indexOf(".java");
            String qualifiedPath = path.substring(begin, end);
            qualifiedPaths.add(qualifiedPath);
            System.out.println(qualifiedPath);
        } else {
            if (file.exists() && file.isDirectory()){
                File[] files = file.listFiles();
                for (File file1 : files){
                    listAllFiles(file1, fileType,qualifiedPaths);
                }
            }
        }
    }

}
