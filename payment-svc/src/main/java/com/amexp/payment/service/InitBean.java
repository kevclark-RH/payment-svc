package com.amexp.payment.service;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.camel.Body;
import org.apache.camel.Exchange;

import com.amexp.payment.service.ClassFinder.Visitor;



public class InitBean {

	public void routeInit(@Body Exchange ex){
		long startTime = System.currentTimeMillis();
		ClassLoader classLoader = ex.getContext().getApplicationContextClassLoader();
		if(classLoader == null){
			System.out.println("ClassLoader is Null.");
			return;
		}
		ClassLoaderClassFinderVisitor visitor = new ClassLoaderClassFinderVisitor(classLoader);
		ClassFinder.findClasses(visitor);
		long endTime = System.currentTimeMillis();
		long differenceTime = endTime - startTime;
		visitor.printLoadedClasses();
		System.out.println("Loaded " + visitor.getCount() + " classes in " +differenceTime + "ms\n\nNOW TRYING PARENT\n\n");	
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
				System.out.println("Error loading a class");
			}
			catch (Exception e) {
				return false;
			}
			return true;
		}
		
		public int getCount(){
			return count;
		}
		
		public void printLoadedClasses(){
			Collections.sort(loadedClasses, String.CASE_INSENSITIVE_ORDER);
			for(String className : loadedClasses){
				System.out.println(className);
			}
		}

		public ClassLoader getClassLoader(){
			return classLoader;
		}
	}
}