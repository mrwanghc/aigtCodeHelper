package com.aigt.code.utils;

import java.io.Serializable;

/**
 * 通用参数对
 * @author leoshen
 * @create 15-04-22
 * @param <T1>
 * @param <T2>
 */
public class Pair<T1 extends Serializable,T2 extends Serializable> implements Serializable{
	private T1 first = null;
	private T2 second = null;
	
	public Pair(){
		
	}
	public Pair( T1 t1 , T2 t2 ){
		this.first = t1;
		this.second = t2;
	}
	
	public T1 getFirst() {
		return first;
	}
	public void setFirst(T1 first) {
		this.first = first;
	}
	public T2 getSecond() {
		return second;
	}
	public void setSecond(T2 second) {
		this.second = second;
	}
}
