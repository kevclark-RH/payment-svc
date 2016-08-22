package com.amexp.payment.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.vfs.VFSUtils;
import org.jboss.vfs.VirtualFile;

public class ClassFinder {

	static Logger logger = Logger.getLogger(ClassFinder.class);
	
	public interface Visitor<T> {
		/**
		 * @return {@code true} if the algorithm should visit more results,
		 *         {@code false} if it should terminate now.
		 */
		public boolean visit(T t);

		public ClassLoader getClassLoader();
	}

	public static void findClasses(Visitor<String> visitor) {

		try {
			logger.info("ClassLoader type: " + visitor.getClassLoader().getClass().getName());
			//using string comparison instead of instance of so I do not have to import the a version of the jboss modules
			if (!(visitor.getClassLoader().getClass().getName().equalsIgnoreCase("org.jboss.modules.ModuleClassLoader"))) {
				logger.info("ClassLoader is not JBoss EAP Module Classloader. Will not find classes.");
				return;
			}
			
			ArrayList<URL> URLs = Collections.list(visitor.getClassLoader().getResources(""));
			for (URL aURL : URLs) {
				if (StringUtils.contains(aURL.toString(), ".jar")) {
					URLConnection conn = aURL.openConnection();
					VirtualFile vf = (VirtualFile) conn.getContent();

					String jarVFSPath = VFSUtils.getPhysicalURL(vf).getFile();

					int idxJarExt = StringUtils.lastIndexOf(jarVFSPath, ".jar");
					int idxSlashAfterJar = StringUtils.indexOf(jarVFSPath, "/", idxJarExt);
					int idxSlashBeforeJar = StringUtils.lastIndexOf(jarVFSPath.substring(0, idxJarExt), "/");

					String jarFileName2 = jarVFSPath.substring(idxSlashBeforeJar + 1, idxJarExt + 4);
					String jarFolder = jarVFSPath.substring(0, idxSlashAfterJar);
					String jarFullPath = jarFolder + "/" + jarFileName2;

					File file = new File(jarFullPath);

					if (file != null && file.exists()) {
						findClasses(file, file, true, visitor);
					} else {
						logger.error("File does not exist.");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
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
			if (file.getName().toLowerCase().endsWith(".jar") && includeJars) {
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
				} catch (Exception ex) {
					logger.error("Unable to visit " + file.getName().toLowerCase());
				}
			} else if (file.getName().toLowerCase().endsWith(".class")) {
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
}