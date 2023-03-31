package com.aigt.code.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {


	public static void main(String[] args){
		for(int i=0; i< 100; i++ ){
			System.out.println(getRandomString());
		}
	}
	
	/**
	 * 生成指定长度随机数字
	 * 
	 * @lastmodified effine[zhangyafei@co-mall.com] 完善方法注释
	 *
	 * @param length
	 *            生成随机数字长度
	 * @return 指定长度随机数字
	 */
	public  static synchronized String getRandomNum(int length){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < length; i++){
			result = result * 10 + array[i];
		}
		StringBuffer resultStr = new StringBuffer();
		resultStr.append(result);
		if(resultStr.length() <length){
			int resultCount = length - resultStr.length();
			for(int i=0;i<resultCount;i++){
				resultStr.append("0");
			}
			return resultStr.toString();
		}
		return String.valueOf(result);
	}


    /**
     * 获取16位随机字符串
     * @return
     */
    public static String getRandomString(){
		//最长16位
		String random = UUID.randomUUID().toString();
		return random.substring(0, Math.min(random.length(), 16));
    }
}
