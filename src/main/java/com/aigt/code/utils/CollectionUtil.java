package com.aigt.code.utils;

import java.util.*;
import java.util.Map.Entry;

/**
 * 集合工具类，继承自spring包
 * @author ShenLiang
 * @create 2014年7月2日
 */
public class CollectionUtil extends org.springframework.util.CollectionUtils{
	
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
			}else{
				for(Entry<K, V> entry : entries) {
					if (value.equals(entry.getValue())) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 切割List集合，将一个大集合切割成指定大小(len)的小集合
	 * @param source
	 * @param len
	 * @param <T>
	 * @return List<List<T>>
	 */
	public static <T> List<List<T>> split(List<T> source, int len) {
		if (isEmpty(source) || len < 1) {
			return Collections.emptyList();
		}
		int size = source.size();
		int count = (size + len - 1) / len;
		List<List<T>> result = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			List<T> subSource = source.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subSource);
		}
		return result;
	}
}
