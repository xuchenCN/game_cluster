package org.mmo.persistent;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class RedisBean {

	public abstract String key();

//	abstract String userKey();
	
	//redisMap make Instance to a map
	public Map<String, String> redisMap() {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());

			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			Map<String, String> propMap = new HashMap<String, String>(props.length);
			for (PropertyDescriptor p : props) {
				if (!propMap.containsKey(p.getDisplayName())) {
					Method m = p.getReadMethod();
					Object value = m.invoke(this);
					if (value != null) {
//						System.out.println(
//								"p.getDisplayName()=" + p.getDisplayName() + "," + "String.valueOf(value)=" + String
//										.valueOf(value));
						propMap.put(p.getDisplayName(), String.valueOf(value));
					}
				}
			}
			return propMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, String>(0);

	}

	public void redisBean(Map<String, String> values) {
		try {

			if (values != null && values.keySet().size() > 0) {


				BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor p : props) {
					if (values.containsKey(p.getDisplayName())) {
						String value = values.get(p.getDisplayName());
						Method m = p.getWriteMethod();
						Class<?> type = p.getPropertyType();
						if (type == byte.class || type == Byte.class) {
							m.invoke(this, Byte.valueOf(value));
						} else if (type == short.class || type == Short.class) {
							m.invoke(this, Short.valueOf(value));
						} else if (type == int.class || type == Integer.class) {
							m.invoke(this, Integer.valueOf(value));
						} else if (type == long.class || type == Long.class) {
							m.invoke(this, Long.valueOf(value));
						} else if (type == float.class || type == Float.class) {
							m.invoke(this, Float.valueOf(value));
						} else if (type == double.class || type == Double.class) {
							m.invoke(this, Double.valueOf(value));
						} else if (type == String.class) {
							m.invoke(this, String.valueOf(value));
						} else if (type == char.class || type == Character.class) {
							m.invoke(this, value.charAt(0));
						}

					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
