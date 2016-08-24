package com.amexp.payment.service;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import com.amexp.payment.service.ClassFinder.Visitor;

public class InitBean {

	static Logger logger = Logger.getLogger(InitBean.class);
	
	public void routeInit(@Body Exchange ex){
		long startTime = System.currentTimeMillis();
		ClassLoader classLoader = ex.getContext().getApplicationContextClassLoader();
		if(classLoader == null){
			logger.error("ClassLoader is Null. Ending Route.");
			return;
		}
		ClassLoaderClassFinderVisitor visitor = new ClassLoaderClassFinderVisitor(classLoader);
		ClassFinder.findClasses(visitor);
		long endTime = System.currentTimeMillis();
		long differenceTime = endTime - startTime;
		
		//enable to see ordered list of loaded classes.
		visitor.printLoadedClasses();
		logger.info("Loaded " + visitor.getCount() + " classes in " +differenceTime + "ms");	
	}

	private class ClassLoaderClassFinderVisitor implements Visitor<String> {
		ClassLoader classLoader;
		ArrayList<String> loadedClasses;
		int count;
		
		public ClassLoaderClassFinderVisitor(ClassLoader classLoader){
			this.classLoader = classLoader;
			count = 0;
			loadedClasses = new ArrayList<String>();
		}
		
		public boolean visit(String t) {
			try {
				
				Class<?> aClass = classLoader.loadClass(t);
				count++;
				loadedClasses.add(aClass.getName());
			}catch(Error e){
				logger.error("Error loading a class. This is most likely expected.");
			}
			catch (Exception e) {
				logger.error("Exception loading a class.");
			}
			return true;
		}
		
		public int getCount(){
			return count;
		}
		
		public void printLoadedClasses(){
			Collections.sort(loadedClasses, String.CASE_INSENSITIVE_ORDER);
			for(String className : loadedClasses){
				logger.trace("Loaded Class" + className);
			}
		}

		public ClassLoader getClassLoader(){
			return classLoader;
		}
	}
}