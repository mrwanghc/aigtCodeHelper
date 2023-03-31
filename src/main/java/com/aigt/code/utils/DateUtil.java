package com.aigt.code.utils;

import com.google.common.base.Joiner;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
/*
* 时间操作工具类
 */
public class DateUtil extends DateTimeUtils {
	/**
	 * 短日期格式
	 */
	public final static String SHORTDATEFORMATER = "yyyy-MM-dd";

	public final static String SHORTTIMEFOR= "HH:mm";
	public final static String SHORTTIMEFORMATER = "HH:mm:ss";
	public final static String SHORTDATETIMEFORMATER = "HHmmss";
	public final static String SHORTDATEFDORMATER = "yyyyMMdd";

	public final static String LONGDATEFORMATER = "yyyy-MM-dd HH:mm:ss";

	public final static String LONGDATEFORMATER_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	/**
	 * Solr中使用的日期格式
	 */
	public static final String SOLR_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:sss'Z'";

	public static final long DAYTIMESTAMP = 24 * 60 * 60 * 1000L;

	/**
	 * yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 */

	public static final String LONGDATE_FORMAT="yyyyMMddHHmmss";

	/**
	 * yyMMddHHmmss 如2009年12月25日9点10分10秒表示为 091225091010
	 */
	public static final String LONGDATEFORMATER_YYMMDDHHMMSS ="yyMMddHHmmss";

	/**
	 *获得纯数字格式的时间
	 */
	public static final String FN_DATE_FORMAT="yyyyMMddHHmmssSSS";
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
    private static final String CMS_SERVER_DATE_FORMAT = "E MMM dd yyyy HH:mm:ss 'GMT'Z";;
	/**
	 * 零点
	 */
	public static final String ZERO = " 00:00:00";

	/**
	 * 24点
	 */
	public static final String TWENTY_FOUR = " 23:59:59";

	public static final String ZERO_SECOND = "00";

	/**
	 * yyyy-MM-dd HH:mm:ss 长日期格式的日期格式化器
	 */
	public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(LONGDATEFORMATER);

	/**
	 * @param datePattern
	 *            格式
	 * @return 返回日期格式字符串（yyyy-mm-dd HH:mm:ss）
	 */
	public static final String getDateByDatePattern(String datePattern) {
		return convertDateToString(datePattern, new Date());
	}

	/**
	 * 把时间装成指定的字符串
	 *
	 * @param datePattern
	 *            转换格式
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateToString(String datePattern, Date date) {
		String returnValue = null;
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(datePattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 把时间装成指定的字符串 (yyyy-MM-dd HH:mm:ss)
	 *
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateToString(Date date) {
		return convertDateToString(LONGDATEFORMATER, date);
	}
	/**
	 * 把时间装成指定的字符串 HHmmss，如2009年12月25日9点10分10秒表示为091010
	 *
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateTimeShortToString(Date date) {
		return convertDateToString(SHORTDATETIMEFORMATER, date);
	}
	/**
	 * 把时间装成指定的字符串 yyyyMMdd，如2009年12月25日9点10分10秒表示为091010
	 *
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateTShortToString(Date date) {
		return convertDateToString(SHORTDATEFDORMATER, date);
	}
	/**
	 * 把时间装成指定的字符串 yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 *
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateTimeToString(Date date) {
		return convertDateToString(LONGDATE_FORMAT, date);
	}

	/**
	 * 把时间字符串转成指定格式的时间
	 *
	 * @param datePattern
	 *            转换格式
	 * @param dateStr
	 *            时间
	 * @return 时间Date
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String datePattern,String dateStr) {
		if( StringUtil.isBlank(dateStr) ){
			return null;
		}

		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(datePattern);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			log.info("{}",pe);
			return null;
		}
		return (date);
	}

	/**
	 * 获取指定日期格式（yyyy-mm-dd ）的当天开始时间
	 * @param dateStr yyyy-mm-dd
	 * @return 带有时分秒的 yyyy-mm-dd 00:00:00
	 */
	public static final Date convertStringToBeginOfDay(String dateStr){
		if( StringUtil.isBlank(dateStr) ){
			return null;
		}
		dateStr=dateStr.trim()+" 00:00:00";
	    return convertStringToDate(LONGDATEFORMATER,dateStr);
	}

	/**
	 * 获取指定日期格式（yyyy-mm-dd ）的当天结束时间
	 * @param dateStr yyyy-mm-dd
	 * @return 带有时分秒的 yyyy-mm-dd 23:59:59
	 */
	public static final Date convertStringToEndOfDay(String dateStr){
		if( StringUtil.isBlank(dateStr) ){
			return null;
		}
		dateStr=dateStr.trim()+" 23:59:59";
		return convertStringToDate(LONGDATEFORMATER,dateStr);
	}

	public static final Date convertStringToDate(String dateStr) {
		if( StringUtil.isBlank(dateStr) ){
			return null;
		}
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(LONGDATEFORMATER);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			log.info("{}",pe);
			return null;
		}
		return (date);
	}

	public static String getStringFormatByTimestamp(java.sql.Timestamp argDate, String fFlag) {
		String strDateTime = "";
		try {
			SimpleDateFormat objSDF = new SimpleDateFormat(fFlag.trim());
			strDateTime = objSDF.format(argDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDateTime;
	}

	public static int getIntFormatByTimestamp(java.sql.Timestamp argDate, String fFlag) {
		String strDateTime = "";
		try {
			SimpleDateFormat objSDF = new SimpleDateFormat(fFlag.trim());
			strDateTime = objSDF.format(argDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StringUtil.intValue(strDateTime,0);
	}

	/**
	 * 把时间字符串装成日期
	 *
	 * @param date
	 *            可传（yyyy-mm-dd HH:mm:ss）或者（yyyy-mm-dd）
	 * @return
	 */
	public static final Date getDateTime(String date) {
		return convertStringToDate(LONGDATEFORMATER, date);
	}

	/**
	 * 判断给定日期是否未过期
	 * @param givenDate 给定日期
	 * @return
	 */
	public static final boolean isNonExpired(Date givenDate){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(calendarNow.getTime());
		Calendar calendarGiven = Calendar.getInstance();
		calendarGiven.setTime(givenDate);
		return calendarNow.before(calendarGiven);
	}

	/**
	 * 判断给定日期是否过期
	 * @param givenDate 给定日期
	 * @return
	 */
	public static final boolean isExpired(Date givenDate){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(calendarNow.getTime());
		Calendar calendarGiven = Calendar.getInstance();
		calendarGiven.setTime(givenDate);
		return calendarNow.after(calendarGiven);
	}

	/**
	 * 判断两个日期大小
	 *
	 * @param firstDate  第一个日期参数
	 * @param secondDate 第二个日期参数
     * @return int 如果 第一个日期参数大于第二个日期 返回 1
	 * 			    如果 两个日期相等 返回0
	 * 			    如果 第一个日期小于第二个日期 返回-1
     */
	public static final int compare( Date firstDate,Date secondDate ){
		return firstDate.compareTo(secondDate);
	}

	public static final Date getDate(){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return calendarNow.getTime();
	}

	/**
	 * 将{@link Date}类型转换为Solr可以使用的字符串
	 * @param date 要转换的日期
	 * @return
	 */
	public static String formatSolrDateString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SOLR_DATE_FORMAT);
		return simpleDateFormat.format(date);
	}

	public static Date DateMinus(Date date,int month){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.MONTH, -month);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysBefore(Date date, int days) {
		Date td = new Date(date.getTime() - DAYTIMESTAMP * days);
		return DateUtil.convertDateToString(SHORTDATEFORMATER, td);
	}

	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysBefore(String dateStr, int days) {
		Date date = convertStringToDate(SHORTDATEFORMATER, dateStr);
		return DateUtil.getDaysBefore(date, days);
	}

	/**
	 * 获取指定日期之前指定天的数组,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysBeforeArray(Date date, int days){
		List<String> resultList = new ArrayList<>();
		for (int i = days-1; i >= 0; i--) {
			resultList.add(getDaysBefore(date, i));
		}
		return resultList;
	}

	/**
	 * 获取指定日期之前指定天的数组,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysBeforeArray(String dateStr, int days){
		List<String> resultList = new ArrayList<>();
		for (int i = days-1; i >= 0; i--) {
			resultList.add(getDaysBefore(dateStr, i));
		}
		return resultList;
	}

	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysAfter(Date date, int days) {
		Date td = new Date(date.getTime() + DAYTIMESTAMP * days);
		return DateUtil.convertDateToString(SHORTDATEFORMATER, td);
	}

	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysAfter(String dateStr, int days) {
		Date date = convertStringToDate(SHORTDATEFORMATER, dateStr);
		return DateUtil.getDaysAfter(date, days);
	}

	/**
	 * 获取指定日期后七天日期
	 * @param date 指定的日期
	 * @return
	 */
	public static Date getSevenDaysBefore(Date date) {
		return new Date(date.getTime() + DAYTIMESTAMP * 7);
	}


	/**
	 * 获取当前时间加一天
	 * @return
	 */
	public static final Date getNextDay() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
		date = calendar.getTime();
		return date;
	}
	/**
	 * 获取当前时间2天后的0点
	 * @return
	 */
	public static final Date getNextTowDay() {
		return getZeroTime(2);
	}

	/**
	 * 获取指定天数的凌晨
	 *
	 * @return 零点 例如2020-10-23 00:00:00
	 */
	public static Date getZeroTime(int dayAmount){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayAmount);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取指定日期之后指定天的数组,包含传入的那一天
	 * @param date 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysAfterArray(Date date, int days){
		List<String> resultList = new ArrayList<>();
		for (int i = 0; i < days; i++) {
			resultList.add(getDaysAfter(date, i));
		}
		return resultList;
	}

	/**
	 * 获取指定日期之后指定天的数组,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysAfterArray(String dateStr, int days){
		List<String> resultList = new ArrayList<>();
		for (int i = 0; i < days; i++) {
			resultList.add(getDaysAfter(dateStr, i));
		}
		return resultList;
	}

	/**
	 * 获取当天0点
	 * @return
	 */
	public static Date atStartOfDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当天23：59：59
	 * @return
	 */
	public static Date atEndOfDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}


	/**
	 * 获取指定日期当天的23：59：59
	 * @return
	 */
	public static Date getEndOfDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}


	/**
	 * 根据时间字符串和秒，给时间设置指定的秒
	 * @param timeStr
	 * @param second
	 * @return
	 */
	public static String setSecond(String timeStr,int second){
		String returnStr="";
		Date d=convertStringToDate(LONGDATEFORMATER,timeStr);
		if(d!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.set(Calendar.SECOND,second);
			returnStr=convertDateToString(LONGDATEFORMATER,cal.getTime());
		}
		return returnStr;
	}

	/**
	 * 获取当时时间前一个小时时间
	 *
	 * @return
	 */
	public static Date getDayBeforeHour(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-1);
		return calendar.getTime();
	}


	/**
	 * 获取当前时间指定小时之后的日期
	 * @param hour 小时(负数为减)
	 * @return Date = currentDate + hour
	 */
	public static Date getDateAddHour(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间指定小时之后的日期
	 * @param hour 小时(负数为减)
	 * @return Date = date + hour
	 */
	public static Date getAppointDateAddHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间指定天数之后的日期
	 * @return Date = date + DAY
	 */
	public static Date getAppointDateAddDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}


	/**
	 * 剪切时间段为时间字符串数组
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static List<String> getStringArray(String beginDateStr,String endDateStr){
		Date beginDate = convertStringToDate(beginDateStr);
		Date endDate = convertStringToDate(endDateStr);
		long diff = endDate.getTime() - beginDate.getTime();
		long temps = 1000 * 60 * 60L;
		long hour = diff / temps;
		List<String> list = new ArrayList<String>();
		if(hour>0){
			list.add(convertDateToString(beginDate));
			for(int i =1 ;i<hour;i++){
				Date d = new Date(beginDate.getTime()+(temps*i));
				list.add(convertDateToString(d));
			}
			list.add(convertDateToString(endDate));
		}else if(hour == 0){
			list.add(convertDateToString(beginDate));
		}
		return list;

	}

	/**
	 * 设置分秒为：59：59
	 * @return
	 */
	public static Date setMinAndSecond(String time){
		Calendar calendar = Calendar.getInstance();
		Date d=convertStringToDate(LONGDATEFORMATER,time);
		calendar.setTime(d);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获得纯数字格式的时间
	 * @param date
	 * @return
	 */
	public static String getFNDateFormat (Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat(FN_DATE_FORMAT);
		return sdf.format(date);
	}
    public static String getServerTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());

        TimeZone aDefault = TimeZone.getDefault();
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.CMS_SERVER_DATE_FORMAT,Locale.US);
        df.setTimeZone(aDefault);
        return df.format(date);
    }

	/**
	 * 格式化
	 *
	 * @param hourAndMinuteTime 时分 例如10:00
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static LocalDateTime formatWithHourAndMinute(String hourAndMinuteTime) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SHORTDATEFORMATER);
		LocalDate date = LocalDate.now();
		// 开始时间10:00 拼接成2020-10-11 10:00:00
		String tempTime = Joiner.on(" ").join(date.format(fmt), Joiner.on(":").join(hourAndMinuteTime, ZERO_SECOND));
		fmt = DateTimeFormatter.ofPattern(DateUtil.LONGDATEFORMATER);
		return LocalDateTime.parse(tempTime, fmt);
	}

	/**
	 * 格式化
	 *
	 * @param date 日期字符串
	 * @param hourAndMinuteTime 时分 例如10:00
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static LocalDateTime formatStringDateAndHourAndMinute(String date, String hourAndMinuteTime) {
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(LONGDATEFORMATER);
		return LocalDateTime.parse(Joiner.on(" ").join(date, Joiner.on(":").join(hourAndMinuteTime, ZERO_SECOND)), fmt);
	}

	/**
	 * 格式化
	 *
	 * @param date 日期
	 * @param hourAndMinuteTime 时分 例如10:00
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static LocalDateTime formatDateAndHourAndMinute(LocalDate date, String hourAndMinuteTime) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SHORTDATEFORMATER);
		DateTimeFormatter fmtDateTime = DateTimeFormatter.ofPattern(LONGDATEFORMATER);
		// 2020-12-20 拼接 10:00:00
		return LocalDateTime.parse(Joiner.on(" ").join(date.format(fmt), Joiner.on(":").join(hourAndMinuteTime, ZERO_SECOND)), fmtDateTime);
	}

	/**
	 * 格式化
	 *
	 * @param hourAndMinuteTime 时分 例如10:00
	 * @return HH:mm:ss
	 */
	public static LocalTime formatHourAndMinute(String hourAndMinuteTime) {
		if (StringUtil.isBlank(hourAndMinuteTime)) {
			return null;
		}
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SHORTTIMEFORMATER);
		// 10:00拼接成 10:00:00
		return LocalTime.parse(Joiner.on(":").join(hourAndMinuteTime, ZERO_SECOND), fmt);
	}

	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		//设置当前时间
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		//设置开始时间
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		//设置结束时间
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		//处于开始时间之后，和结束时间之前的判断
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static LocalDate getYesterdayLocalDate(){
		return LocalDate.now().plusDays(-1);
	}

	/**
	 * 将日期字符串格式化成localDate，目前仅支持两种日期格式：
	 * yyyy-MM-dd
	 * yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期字符串
	 * @return 转换成的localDate对象
	 */
	public static LocalDate parseLocalDate(String date) {
		// 日期为null，返回null
		if (StringUtil.isBlank(date)) {
			return null;
		}
		try {
			// 先尝试根据“yyyy-MM-dd”解析字符串
			return LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			// 根据“yyyy-MM-dd”无法解析字符串后，根据“yyyy-MM-dd HH:mm:ss”解析，仍解析不了的，抛出异常
			return LocalDate.parse(date, LONG_DATETIME_FORMATTER);
		}
	}

	/**
	 * 将日期字符串格式化成localDateTime，目前仅支持两种日期格式：
	 * yyyy-MM-dd HH:mm:ss
	 * ISO日期，例：2007-12-03T10:15:30
	 *
	 * @param date 日期字符串
	 * @return 转换成的localDateTime对象
	 */
	public static LocalDateTime parseLocalDateTime(String date) {
		// 日期为null，返回null
		if (StringUtil.isBlank(date)) {
			return null;
		}
		try {
			// 先尝试根据“yyyy-MM-dd”无法解析字符串后，根据“yyyy-MM-dd HH:mm:ss”解析，仍解析不了的，抛出异常
			return LocalDateTime.parse(date, LONG_DATETIME_FORMATTER);
		} catch (DateTimeParseException e) {
			// 再尝试根据ISO日期格式解析字符串 例：“2007-12-03T10:15:30”
			return LocalDateTime.parse(date);
		}
	}

	/**
	 * 相差秒数
	 *
	 * @param time 指定时间
	 * @return     指定时间与现在相差的秒数
	 */
	public static long secondsDifference(LocalDateTime time) {
		if (Objects.isNull(time)) {
			return BigDecimal.ZERO.longValue();
		}
		return time.toEpochSecond(ZoneOffset.of("+8")) - System.currentTimeMillis() / 1000L;
	}

	/**
	 * 指定时间与当年最后一天相差天数
	 *
	 * @param time 指定时间
	 * @return 	   指定时间与当年最后一天相差天数
	 */
	public static long daysDifferenceWithLastDayOfYear(LocalDateTime time) {
		if (Objects.isNull(time)) {
			return BigDecimal.ZERO.longValue();
		}
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());
		return lastDayOfYear.toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay();
	}

	/**
	 * 将LocalDateTime格式化成Data
	 * @param time 日期: yyyy-MM-dd HH:mm:ss
	 * @return 转换成Date:
	 */
	public static Date parseLocalDateTimeToDate(LocalDateTime time) {
		// 日期为null，返回null
		if (Objects.isNull(time)) {
			return null;
		}
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = time.atZone(zoneId);
		return Date.from(zdt.toInstant());
	}
}
