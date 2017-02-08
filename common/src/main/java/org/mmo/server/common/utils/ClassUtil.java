package org.mmo.server.common.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassUtil {

	public static void addClasspath(String path) throws Exception {
		System.out.println("Adding path " + path + " to class path");
		File file = new File(path);

		if (file.exists()) {
			URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			Class<URLClassLoader> urlClass = URLClassLoader.class;
			Method method = urlClass.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(urlClassLoader, new Object[] { file.toURI().toURL() });
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> forName(String name, Class<T> clz) throws ClassNotFoundException {
		return (Class<? extends T>) Class.forName(name);
	}

	public static Object newInstance(String clz) {
		try {
			return forName(clz, Object.class).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void updateFinalField(Class<?> clz, String fieldName, Object obj, Object newValue) {
		try {
			Field field = clz.getDeclaredField(fieldName);
			field.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(obj, newValue);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
