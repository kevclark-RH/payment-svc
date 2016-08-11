package com.payment.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.payment.service.ClassFinder.Visitor;



public class InitBean {

	public void init() {
		long startTime = System.currentTimeMillis();
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		ClassLoaderClassFinderVisitor visitor = new ClassLoaderClassFinderVisitor(classLoader);
		ClassFinder.findClasses(visitor);
		long endTime = System.currentTimeMillis();
		long differenceTime = endTime - startTime;
		//visitor.printLoadedClasses();
		System.out.println("Loaded " + visitor.getCount() + " classes in " +differenceTime + "ms");
	}
	
	public void listLoadedClasses(){
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		listLoadedClasses(classLoader);
	}
	
	private void listLoadedClasses(ClassLoader byClassLoader) {
	    Class clKlass = byClassLoader.getClass();
	    System.out.println("Classloader: " + clKlass.getCanonicalName());
	    int count = 0;
	    while (clKlass != java.lang.ClassLoader.class) {
	        clKlass = clKlass.getSuperclass();
	    }
	    try {
	        java.lang.reflect.Field fldClasses = clKlass
	                .getDeclaredField("classes");
	        fldClasses.setAccessible(true);
	        Vector classes = (Vector) fldClasses.get(byClassLoader);
	        for (Iterator iter = classes.iterator(); iter.hasNext();) {
	            Object next = iter.next();
	        	//System.out.println("Listing " + next);
	            count++;
	        }
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("Listed " + count + " classes in classloader");
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
		
		@Override
		public boolean visit(String t) {
			try {
				Class aClass = classLoader.loadClass(t);
				//System.out.println("Loaded " + aClass);
				count++;
				loadedClasses.add(t);
			}catch(Error e){
				//System.out.println("Catching error: "+ e.getMessage() +" . At "+ t);
			}
			
			catch (Exception e) {
				//System.out.println("error loading class");
				//e.printStackTrace();
				return false;
			}

			return true;
		}
		
		public int getCount(){
			return count;
		}
		
		public void resetCount(){
			count = 0;
		}
		
		public void printLoadedClasses(){
			loadedClasses.sort(String.CASE_INSENSITIVE_ORDER);
			for(String className : loadedClasses){
				System.out.println(className);
			}
		}
	}
}