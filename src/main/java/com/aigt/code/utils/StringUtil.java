package com.aigt.code.utils;

import com.aigt.code.validator.reg.Reg4Validator;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类，继承自spring包
 * 
 * @author whc
 */
public class StringUtil extends org.springframework.util.StringUtils {

	private static final String EMPTY = "";
	/**
	 * <pre>
	 * StringUtils.isNotEmpty(null)		false
	 * StringUtils.isNotEmpty("")			false
	 * StringUtils.isNotEmpty("null")		true
	 * StringUtils.isNotEmpty("  ")		true
	 * StringUtils.isNotEmpty(" a ")		true
	 * @param str
	 * @return boolean 是否为空
	 * </pre>
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	/**
	 * <pre>
	 * StringUtils.isEmpty(null)		true
	 * StringUtils.isEmpty("")		true
	 * StringUtils.isEmpty("null")	false
	 * StringUtils.isEmpty("  ")		false
	 * StringUtils.isEmpty(" a ")		false
	 * @param str
	 * @return boolean 是否为空
	 * </pre>
	 */
	public static boolean isEmpty(String str) {
		return org.springframework.util.StringUtils.isEmpty(str);
	}
	
	/**
	 * <pre>
	 * StringUtils.isNotBlank(null)		false
	 * StringUtils.isNotBlank("")			false
	 * StringUtils.isNotBlank("null")		true
	 * StringUtils.isNotBlank("  ")		false
	 * StringUtils.isNotBlank(" a ")		true
	 * @param str
	 * @return boolean 是否为空
	 * </pre>
	 */
	public static boolean isNotBlank(String str) {
		if(isNotEmpty(str) && isNotEmpty(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * <pre>
	 * StringUtils.isBlank(null)		true
	 * StringUtils.isBlank("")		true
	 * StringUtils.isBlank("null")	false
	 * StringUtils.isBlank("  ")		true
	 * StringUtils.isBlank(" a ")		false
	 * @param str
	 * @return boolean 是否为空
	 * </pre>
	 */
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}
	
	/**
	 * 格式化详细地址
	 * 
	 * @return
	 */
	public static String getDetailAddressStr(String address,boolean isChinese) {
		String[] array = address.split("\\%\\$");
		
		
		Map<Integer,String> unitMap = new HashMap<>();
		unitMap.put(0,isChinese?"路":" Road ");
		unitMap.put(1,isChinese?"号/弄":" No./Figure ");
		unitMap.put(2,isChinese?"小区":" Housing estate ");
		unitMap.put(3,isChinese?"号/幢":" Building No. ");
		unitMap.put(4,isChinese? "室 ":" Room ");	
		StringBuffer sb = new StringBuffer();
		String tmp ;
		for(int i=0;i<array.length;i++){
			tmp = array[i] ;
			tmp +=unitMap.get(i);
			sb.append(tmp);
		}
		/*if (array.length != 5) {
			return address;
		}
		StringBuffer sb = new StringBuffer();
		String tmp ;
		tmp = array[0] ;
		tmp +=isChinese?"路 ":" road ";
		sb.append(tmp);
		
		if(StringUtils.isNotEmpty(array[1])){
			tmp = array[1] ;
			tmp += isChinese? "号/弄":" No./Figure ";
			sb.append(tmp);
		}
		if(StringUtils.isNotEmpty(array[2])){
			tmp = array[2] ;
			tmp += isChinese? "小区":" Housing estate ";
			sb.append(tmp);
		}	
		
		tmp = array[3] ;
		tmp += isChinese? "楼":" Building No. ";
		sb.append(tmp);
		
		tmp = array[4] ;
		tmp += isChinese? "室 ":" Room ";
		sb.append(tmp);*/
		
		return sb.toString();
	}

	/**
	 * 根据占位符替换
	 * @return
	 */
	public static String format(String source, Map<String, Object> map) {
		String result = source;
		if(!isNotEmpty(source)){
			return "";
		}
		if (CollectionUtil.isNotEmpty(map)) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				result = result.replace(entry.getKey(), entry.getValue()==null?"": entry.getValue().toString());
			}
		}
		return result;
	}

	public static String stringArrayToStr( String[] arr ){
		if( arr == null ) return "";
		if( arr.length == 0 ) return "";
		StringBuilder sb = new StringBuilder(arr.length*3);
		sb.append("[");
		for( int i = 0, len = arr.length; i < len; i++)
		{
		   if( i > 0 ){
		      sb.append(",");
		   }
		 
		   sb.append(arr[i]);
		}
		return sb.append("]").toString();
	}
	
	/**
	 * 将字符串中的HTML符号转义
	 * 
	 * @param html
	 *            需要转义的字符串
	 * @return 转义后的字符串
	 */
	public static String unhtml(String html) {
		if (html == null)
			return "";
		html = html.replaceAll("&", "&amp;");
		html = html.replace("\"", "&quot;");
		html = html.replace("'", "&#39;");
		html = html.replace("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		return html;
	}
	
	/**
	 * 判断一个字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length();--i>=0;){   
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将给定字符串转换成数字，无法转换的时候使用默认值赋值
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int intValue(String str, int defaultValue) {
		if (null != str && !str.isEmpty() && isNumeric(str)) {
			return Integer.valueOf(str);
		}
		return defaultValue;
	}
	
	/**
     * 半角转全角
     * @param input String.
     * @return 全角字符串.
     */
    public static String toSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String toDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
    }
 
    /**
     * 去空格处理 :返回去除空格后的对象,若是null/""/" ",返回""
     * @param 
     * @return 
     */
	public static String trimToEmpty(String str) {
		return str == null ? EMPTY : str.trim();
	}
    	
	// Joining
    //-----------------------------------------------------------------------
    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No separator is added to the joined String.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param <T> the specific type of values to join together
     * @param elements  the values to join together, may be null
     * @return the joined String, {@code null} if null array input
     * @since 2.0
     * @since 3.0 Changed signature to use varargs
     */
    @SafeVarargs
	public static <T> String join(T... elements) {
        return join(elements, null);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, {@code null} if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, {@code null} if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        
        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, {@code null} if null array input
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A {@code null} separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, {@code null} if null array input
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 将使用,号隔开的字符串转换为int数组
     * @param string
     * @return
     * @throws NumberFormatException
     */
	public static int[] parseStringToArray(String string) throws NumberFormatException {
		String[] stringArray = string.split(",");
		int[] intArray = new int[stringArray.length];
		if (stringArray.length > 0) {
			for (int i = 0; i < stringArray.length; i++) {
				intArray[i] = Integer.parseInt(stringArray[i]);
			}
		}
		return intArray;
	}

	public static String parseStringListToString(List<String> stringList){
        StringBuilder subSiteIdsStr = new StringBuilder("'");
        stringList.forEach( s -> {
            subSiteIdsStr.append(s).append("','");
        });

        String str = subSiteIdsStr.toString();
        if(str.length() > 0){
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }
    public static String parseIntListToString(List<Integer> stringList){
        StringBuilder subSiteIdsStr = new StringBuilder();
        stringList.forEach( s -> {
            subSiteIdsStr.append(s).append(",");
        });

        String str = subSiteIdsStr.toString();
        if(str.length() > 0){
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

	/**
	 * 构建多参数查询sql
	 * @param values
	 * @param source
	 */
	public static String buildSubSql(List<Object> values, Collection<Integer> source){
		if (CollectionUtils.isEmpty(source)) {
			return "";
		}
		StringBuilder sql = new StringBuilder(" (");
		int i = 0;
		for (Integer id : source) {
			if (i > 0) {
				sql.append(",");
			}
			sql.append("?");
			values.add(id);
			i++;
		}
		return sql.append(") ").toString();
	}


	/**
	 * 生成x位随机字母数字组合
	 * @param length
	 * @return
	 */
	public static String getCharAndNum(int length) {
		Random random = new Random();
		StringBuilder result = new StringBuilder();
		String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
		int charLength = charStr.length();
		for (int i = 0; i < length; i++) {
			result.append(charStr.charAt(random.nextInt(charLength)));
		}
		return result.toString();
	}

	/**
	 * 手机号中间四位星号替代
	 *
	 * @param mobile 手机号
	 * @return 星号替换后的结果 例如 183****2345
	 */
	public static String mobileStarSecret(String mobile) {
		if (StringUtil.isBlank(mobile)) {
			return "";
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	}


	/**
	 * 供应商分摊值范围0-1之间，保留4位小数
	 * @param ratioValue
	 * @return
	 */
	public static boolean ratioPattern(String ratioValue){
		String regexp = "^(0(\\.\\d{1,4})?|1(\\.0{1,4})?)$";
		Pattern pattern = Pattern.compile(regexp);
		return pattern.matcher(ratioValue).matches();
	}

	/**
	 * 获取指定数量的sql占位符
	 * @param number 数量
	 * @return 返回"?,?,?"
	 */
	public static String getSqlPlaceHolder(int number) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < number; i++) {
			temp.append("?,");
		}
		if (temp.length() > 0) {
			temp.deleteCharAt(temp.length() - 1);
		}
		return temp.toString();
	}

	/**
	 * 将手机号，中间四位替换为*
	 * @param mobile 手机号
	 * @return 183****8989
	 */
	public static String mobileToStar(String mobile){

		if(isEmpty(mobile)){
			return null;
		}

		//手机号码为11位的数字
		String regexp = Reg4Validator.MOBILEPHONE.getRegexp();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(mobile);
		if(matcher.find()){
			return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		}
		return null;
	}
}