package com.aigt.code.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 集合工具类，集成自spring包
 * @author ShenLiang
 * @create 2014年7月2日
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils{
	
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}
	/**
	 * 根据 map中的 value找 key，<br/>
	 * 使用时请 确保value不重复，且key不包含null，<br/>
	 * 请谨慎使用
	 * @param value
	 * @param map
	 * @return  
	 */
	public static <K,V> K getKeyByValue(V value,Map<K, V> map) {
		if (isNotEmpty(map)) {
			Set<Entry<K, V>> entries = map.entrySet(); 
			if (value == null) {
				for(Entry<K, V> entry : entries) {
					if (entry.getValue() == null) {
						return entry.getKey();
					}
				}
				return null;
			}
			for(Entry<K, V> entry : entries) {
				if (value.equals(entry.getValue())) {
					return entry.getKey();
				}
			}
		}
		return null;
	}


	/**
	 * 获取所有指定属性的列表
	 *
	 * @param pos
	 * @return
	 */
	public static <T> List<T> getPropertyList(List<?> pos, String propertyName, Class<?> T) {
		Class<?> clz = null;
		List<T> propertyList = new ArrayList<T>();
		try {
			for (int i = 0; i < pos.size(); i++) {
				Object obj = pos.get(i);
				if (clz == null) {
					clz = obj.getClass();
				}
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
				propertyList.add((T) propertyDescriptor.getReadMethod().invoke(obj));
			}
		} catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return propertyList;
	}
}
