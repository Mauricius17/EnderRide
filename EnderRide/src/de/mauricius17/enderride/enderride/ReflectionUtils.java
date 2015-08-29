package de.mauricius17.enderride.enderride;

import java.lang.reflect.Field;

public class ReflectionUtils {
	
	public static Field getField(String paramString, Class<?> paramClass) {
		 Field field = null;
		 
	     try {
	         field = paramClass.getDeclaredField(paramString);
	         field.setAccessible(true);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	     return field;
	}
}
