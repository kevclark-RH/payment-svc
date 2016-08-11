package com.payment.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.cxf.helpers.IOUtils;
import org.springframework.core.io.VfsResource;

public class ClassFinder {
	
	public interface Visitor<T> {
	    /**
	     * @return {@code true} if the algorithm should visit more results,
	     * {@code false} if it should terminate now.
	     */
	    public boolean visit(T t);
	    
	    public String[] getJarPaths();
	    
	    public ClassLoader getClassLoader();
	}
	
    public static void findClasses(Visitor<String> visitor) {
        String classpath = System.getProperty("java.class.path");
        String[] paths = visitor.getJarPaths();

//        String javaHome = System.getProperty("java.home");
//        File file = new File(javaHome + File.separator + "lib");
//        if (file.exists()) {
//            findClasses(file, file, true, visitor);
//        }

        for (String path : paths) {
        	System.out.println("Path: " + path);
        	File file = null;
                InputStream inputStream = visitor.getClassLoader().getResourceAsStream(path);
                if(inputStream == null){
                	System.out.println("InputStream Null");
                	continue;
                }
                try {
					file = stream2file(inputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            
            if (file != null && file.exists()) {
                findClasses(file, file, true, visitor);
            }else{
            	System.out.println("File does not exist.");
            }
        }
    }

    private static boolean findClasses(File root, File file, boolean includeJars, Visitor<String> visitor) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                if (!findClasses(root, child, includeJars, visitor)) {
                    return false;
                }
            }
        } else {
        	System.out.println(file.getName().toLowerCase());
            if (file.getName().toLowerCase().endsWith(".jar") && includeJars) {
            	System.out.println("found jar: " + file.getName().toLowerCase());
                JarFile jar = null;
                try {
                    jar = new JarFile(file);
                
                if (jar != null) {
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        int extIndex = name.lastIndexOf(".class");
                        if (extIndex > 0) {
                            if (!visitor.visit(name.substring(0, extIndex).replace("/", "."))) {
                                jar.close();
                            	return false;
                            }
                        }
                    }
                }
                jar.close();
                }
                
                
                catch (Exception ex) {
                	System.out.println("unable to visit " + file.getName().toLowerCase());
                }
            }
            else if (file.getName().toLowerCase().endsWith(".class")) {
                if (!visitor.visit(createClassName(root, file))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static String createClassName(File root, File file) {
        StringBuffer sb = new StringBuffer();
        String fileName = file.getName();
        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
        file = file.getParentFile();
        while (file != null && !file.equals(root)) {
            sb.insert(0, '.').insert(0, file.getName());
            file = file.getParentFile();
        }
        return sb.toString();
    }
    
    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";

    public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        System.out.print("Created File");
        return tempFile;
    }
    
}