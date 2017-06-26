package com.sglj.fbf.utils;

import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * @author guanhongwei
 *
 */
public class DateUtil {
    //~ Static fields/initializers -----------------------------------------------------------------

	/**
	 * DatePattern - yyyy-MM-dd
	 */
    public static final String defaultDatePattern = "yyyy-MM-dd";
    
    /**
	 * TimePattern - HH:mm:ss.SSS
	 */
    public static final String defaultTimePattern = "HH:mm:ss.SSS";
    
    /**
     * TimeStampPattern - yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String defaultTimeStampPattern = "yyyy-MM-dd HH:mm:ss.SSS";

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * 返回一个指定日期的Calendar实例
     *
     * @param date
     *
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 返回当天的Calendar实例
     *
     * @return
     */
    public static Calendar getCurrentCalendar() {
        Date     date     = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 返回当前日期
     *
     * @return date 当前日期
     */
    public static Date getCurrentTime() {
        return new Date();
    }

    /**
     * 按照默认格式返回当前日期
     *
     * @return date 当前日期
     */
    public static String getCurrentTimeByDefaultPattern() {
        return new SimpleDateFormat(defaultDatePattern).format(new Date());
    }

    /**
     * 返回指定日期的默认格式字符串输出
     *
     * @param date 指定日期
     *
     * @return
     */
    public static String getTimeByDefaultPattern(Date date) {
        return new SimpleDateFormat(defaultDatePattern).format(date);
    }

    /**
     * 返回指定日期的默认格式字符串输出
     *
     * @param date 指定日期
     * @param pattern DOCUMENT ME!
     *
     * @return
     */
    public static String getTimeByCustomPattern(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 按照自定义格式返回当前日期
     *
     * @param pattern DOCUMENT ME!
     *
     * @return date 当前日期
     */
    public static String getCurrentTimeByCustomPattern(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 判断日期是否属于自然季度的末尾月
     *
     * @param date 日期
     *
     * @return
     */
    public static boolean isEndOfSeason(Date date) {
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);

        if ((month % 3) == 2) {
            return true;
        }

        return false;
    }

    /**
     * 返回月
     *
     * @param date DOCUMENT ME!
     *
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日
     *
     * @param date DOCUMENT ME!
     *
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回年
     *
     * @param date
     *
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回某月的天数
     *
     * @param date DOCUMENT ME!
     *
     * @return
     */
    public static int getMonthLength(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回该月可能的最大日期
     *
     * @param date
     *
     * @return
     */
    public static Date getActualMaximumDate(Date date) {
        Calendar calendar         = getCalendar(date);
        int      actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMaximumDay);

        return calendar.getTime();
    }

    /**
     * 返回该月可能的最小日期
     *
     * @param date
     *
     * @return
     */
    public static Date getActualMinimumDate(Date date) {
        Calendar calendar         = getCalendar(date);
        int      actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);

        return calendar.getTime();
    }

    /**
     * 获得date当月的指定的某一天
     *
     * @param date
     * @param indexDay
     *
     * @return
     */
    public static Date getSpecifyDate(Date date, int indexDay) {
        Calendar calendar         = getCalendar(date);
        int      actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, (actualMininumDay + indexDay) - 1);

        return calendar.getTime();
    }

    /**
     * 将日期转换成YearMonth <br> pattern : yyyyMM
     *
     * @param date
     *
     */
    public static String getYearMonth(Date date) {
        return new SimpleDateFormat("yyyyMM").format(date);
    }

    /**
     * 获取上个月的日期对象(上个月1号)
     *
     * @param date
     *
     */
    public static Date getPriorMonthDate(Date date) {
        Calendar calendar    = getCalendar(date);
        int      month       = calendar.get(Calendar.MONTH);
        int      year        = calendar.get(Calendar.YEAR);
        Calendar newCalendar = Calendar.getInstance();

        if (month == 0) {
            year--;
            month = 11;
        } else {
            month--;
        }

        newCalendar.set(year, month, 1);

        return newCalendar.getTime();
    }

    /**
     * 
     * 返回上季度最后一天
     * @param date
     *
     * @return Date
     */
    public static Date getpriorMonthDateByMonth(Date date) {
        Calendar calendar    = getCalendar(date);
        int      month       = calendar.get(Calendar.MONTH) + 1;
        int      year        = calendar.get(Calendar.YEAR);
        Calendar newCalendar = Calendar.getInstance();

        if ((month == 4) || (month == 5) || (month == 6)) {
            newCalendar.set(year, 3, 0);

            return newCalendar.getTime();
        } else if ((month == 7) || (month == 8) || (month == 9)) {
            newCalendar.set(year, 6, 0);

            return newCalendar.getTime();
        } else if ((month == 10) || (month == 11) || (month == 12)) {
            newCalendar.set(year, 9, 0);

            return newCalendar.getTime();
        } else {
            year--;
            newCalendar.set(year, 12, 0);

            return newCalendar.getTime();
        }
    }

    /**
     * 去掉日期的时、分、秒，如果没有指定日期，则返回当前日期
     *
     * @param date
     *
     * @return
     */
    public static Date getSimpleDate(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

	/**
	 * 设置日期年月日 时分秒 - 24 时制
	 * @param date 日期,若为空则抛错
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @throws Exception
	 */
	public static Date setDateTime(Date date, Integer hour, Integer minute, Integer second){
		if (date == null) {
			return new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
        if (hour != null) {
        	cal.set(Calendar.HOUR_OF_DAY, hour);//小时
		}
		if (minute != null) {
			cal.set(Calendar.MINUTE,minute);//分     
		}
		if (second != null) {
			cal.set(Calendar.SECOND,second);//秒
		}
        return cal.getTime();
	}
	
	/**
	 * 设置date的最小时间 如:2014-09-05 00:00:00
	 * @param date
	 * @return
	 */
	public static Date setDateTimeMin(Date date){
		return setDateTime(date, 00, 00, 00);
	}
	
	/**
	 * 设置date的最大时间 如:2014-09-05 23:59:59
	 * @param date
	 * @return
	 */
	public static Date setDateTimeMax(Date date){
		return setDateTime(date, 23, 59, 59);
	}
    
	/**
	 * 获取上个月的第一天
	 * @param date
	 * @return
	 */
    public static Date getLastMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 获取下个月的最后一天
     * @param date
     * @return
     */
    public static Date getNextMonth(Date date) {
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return getActualMaximumDate(calendar.getTime());
    }

    /**
     * 按照默认格式转换String 为日期
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date parse(String str) throws ParseException {
        return parse(str, defaultDatePattern);
    }

    /**
     * 按照指定格式转换String 为日期
     * @param str
     * @param pattern
     *
     */
    public static Date parse(String str, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(str);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换错误", e);
        }
    }

    /**
     * 获得前months月的最后一天
     *
     * @param date
     * @param months
     *
     * @return
     */
    public static Date parse(Date date, int months) {
        String   str      = getTimeByCustomPattern(date, "yyyy-MM");
        Date     d        = parse(str, "yyyy-MM");
        Calendar calendar = getCalendar(d);
        int      month    = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, (month - months));

        return calendar.getTime();
    }

    /**
     * 获得前months月的最后一天
     *
     * @param date
     * @param months
     *
     * @return
     */
    public static Date getLastDay(Date date, int months) {
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, (month - months));
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return getActualMaximumDate(calendar.getTime());
    }

    /**
     * 获得前months月的最后一天
     *
     * @param date
     * @param months
     *
     * @return
     */
    public static Date getFirstDay(Date date, int months) {
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, (month - months));
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return getActualMinimumDate(calendar.getTime());
    }

    /**
     * 两个月间隔的月份
     *
     * @param beginDate 开始日期
     * @param endDate 结束日期
     *
     * @return
     */
    public static int getInterval(Date beginDate, Date endDate) {
        int b  = getMonth(beginDate);
        int e  = getMonth(endDate);
        int by = getYear(beginDate);
        int ey = getYear(endDate);

        return e - b + (12 * (ey - by));
    }

    /**
     * 两个日间隔的天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getDayInterval(Date beginDate, Date endDate) {
        String beginStr = getTimeByCustomPattern(beginDate, defaultDatePattern);
        String endStr   = getTimeByCustomPattern(endDate, defaultDatePattern);
        long   begin    = parse(beginStr, defaultDatePattern).getTime();
        long   end      = parse(endStr, defaultDatePattern).getTime();
        long   days     = (end - begin) / (long) (1000 * 3600 * 24);

        if (days < 0) {
            days = 0;
        }

        return days;
    }

    /**
     * 返回前一天
     *
     * @param date
     *
     * @return
     */
    public static Date getPreviousDay(Date date) {
        Calendar calendar = getCalendar(date);
        int      day      = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day - 1);

        return calendar.getTime();
    }

    /**
     * 是否是季度末
     * @param date
     * @return
     */
    public static boolean isEndQuarter(Date date) {
        boolean  retval   = false;
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);

        if (((month + 1) % 3) == 0) {
            retval = true;
        }

        return retval;
    }

    /**
     * 是否是 6 月
     * @param date
     * @return
     */
    public static boolean isMidYear(Date date) {
        boolean  retval   = false;
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);

        if (((month + 1) % 6) == 0 && month != 11) {
            retval = true;
        }

        return retval;
    }

    /**
     * 是否是年末
     * @param date
     * @return
     */
    public static boolean isEndYear(Date date) {
        boolean  retval   = false;
        Calendar calendar = getCalendar(date);
        int      month    = calendar.get(Calendar.MONTH);

        if (((month + 1) % 12) == 0) {
            retval = true;
        }

        return retval;
    }

    /**
     * 日期型转换为字符串
     * yyyy-MM-dd
     * @param date
     *
     * @return
     */
    public static String convertToString(Date date) {
    	if(date == null){
    		return null;
    	}
        return new SimpleDateFormat(defaultDatePattern).format(date);
    }

    /**
     * java.util.date 转换为 java.sql.date
     *
     * @param date
     *
     * @return
     */
    public static java.sql.Date convertToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 全格式 格式化当前日期
     * @return
     */
    public static String getCurrentTimeByFullPattern() {
        return new SimpleDateFormat(DateUtil.defaultTimeStampPattern).format(new Date());
    }

    /**
     * Long形字符串转化日期
     *
     * @param exeDate
     *
     * @return
     */
    public static String convertToDateString(Object exeDate) {
        String exeDateStr = "";

        if ((exeDate != null) && exeDate instanceof String) {
            long dataValue = Long.parseLong((String) exeDate);
            Date date      = new Date(dataValue);
            exeDateStr     = DateUtil.getTimeByCustomPattern(date, "yyyy-MM-dd");
        }

        return exeDateStr;
    }

    /**
     * 获得指定日期的前days天日期
     *
     * @param date
     * @param days
     *
     * @return
     */
    public static Date getPriorDay(Date date, int days) {
        long curTime   = date.getTime();
        long priorTime = curTime - (1000 * 60 * 60 * 24 * days);

        return new Date(priorTime);
    }

    /**
     * 返回指定日期的下一天
     *
     * @param date
     *
     * @return
     */
    public static Date getNextDay(Date date) {
        Date     mydate   = (null == date) ? new Date() : date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mydate);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);

        return calendar.getTime();
    }

    /**
     * 返回指定日期的前一天
     *
     * @param date
     *
     * @return
     */
    public static Date getLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);

        return calendar.getTime();
    }

    /**
     * 获得当月的第一天
     *
     * @param date
     *
     * @return
     */
    public static Date getFirstDayOfThisMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 获得上月的第一天
     *
     * @param date
     *
     * @return
     */
    public static Date getFirstDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 返回当月的最后一天
     *
     * @param strDate 格式（'YYYY-MM'）
     *
     * @return
     *
     * @throws BusinessException DOCUMENT ME!
     */
    public static Date getLastDayOfThisMonth(String strDate)
                                      throws Exception {
        try {
            Date     date     = new SimpleDateFormat("yyyy-MM").parse(strDate.trim());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + 1));

            calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) - 1));

            return calendar.getTime();
        } catch (ParseException e) {
            throw new Exception("日期格式错误！");
        }
    }

    /**
     * 把Date型数据转换成XML传输日期型数据使用的类型
     *
     * @param date
     *
     * @return
     *
     * @throws BusinessException
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date)
        throws Exception {
        if (date == null) {
            return null;
        }

        try {
            DatatypeFactory   factory  = DatatypeFactory.newInstance();
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            return factory.newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            throw new Exception("日期类型错误！");
        }
    }
    
    /**
     * 把XML传输日期型数据使用的类型转换成Date型数据
     * @param gregorianCalendar
     * @return
     */
    public static Date convertToDate(XMLGregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null) {
            return null;
        }

        Calendar calendar = new GregorianCalendar();
        calendar = gregorianCalendar.toGregorianCalendar();

        return calendar.getTime();
    }

    /**
     * 把XML传输日期型数据使用的类型转换成 Timestamp 型数据
     * @param gregorianCalendar
     * @return
     */
    public static Timestamp convertToTimestamp(XMLGregorianCalendar gregorianCalendar) {
        Date date = convertToDate(gregorianCalendar);

        if (date == null) {
            return null;
        }

        return new Timestamp(date.getTime());
    }

    /**
     * 按照默认格式返回日期
     *
     * @param strDate
     *
     * @return
     *
     * @throws BusinessException
     */
    public static Date getDateByDefaultPattern(String strDate)
                                        throws Exception {
        try {
            Date     date     = new SimpleDateFormat(defaultDatePattern).parse(strDate.trim());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
            calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH)));
            calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) - 1));

            return calendar.getTime();
        } catch (ParseException e) {
            throw new Exception("日期格式错误！");
        }
    }
    
    /**
     * 字符型日期转换成Timestamp类型
     * 
     * @param datestr 字符类型  格式为 yyyy-MM-dd HH:mm:ss.SSS
     * @return  Timestamp 类型的数据
     * 
     * @author xingchang.zhang 
     * @since 2008-06-03
     */
    public static Timestamp convertStrToTimestamp(String datestr) throws Exception {
    	
        SimpleDateFormat sdf = new SimpleDateFormat(defaultTimeStampPattern);
        Timestamp ts;
		try {
			ts = new Timestamp(sdf.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new Exception("日期格式错误！");
		}
        return ts;
    }
        
    /**
     * 判断是否在时间范围内
     *
     * @param checkDate
     * @param startDate
     * @param endDate
     *
     * @return
     */
    public static boolean isDateBetween(Date checkDate, Date startDate, Date endDate) {
        // zxs add
        if (null == checkDate) {
            return true;
        }

        if ((null != endDate) && checkDate.before(endDate) && (null == startDate)) {
            return true;
        }

        if (
            (null != startDate) && (checkDate.after(startDate) || checkDate.equals(startDate)) &&
                (null == endDate)) {
            return true;
        }

        //
        if ((null == checkDate) || (null == startDate) || (null == endDate)) {
            return false;
        }

        if (
            (checkDate.before(endDate) && checkDate.after(startDate)) ||
                (checkDate.getTime() == startDate.getTime()) ||
                (checkDate.getTime() == endDate.getTime())) {
            return true;
        }

        return false;
    }

    /**
     * 按照默认格式返回日期
     *
     * @param strDate
     *
     * @return
     *
     * @throws BusinessException
     */
    public static Timestamp getTimestampByDefaultPattern(String strDate)
                                                  throws Exception {
        return new Timestamp(getDateByDefaultPattern(strDate).getTime());
    }
    
    /**
     * 计算年龄
     * @param birthDay
     * @param nowDate
     * @return
     * @throws Exception
     */
    public static String getAge(Date birthDay, Date nowDate) throws Exception {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(nowDate);
    	
    	if (cal.before(birthDay)) {
    		throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
    	}
    	
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH)+1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        
        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                //monthNow<monthBirth
                age--;
            }
        }

        return age +"";
    }
    
    /**
     * 计算时间
     * @param date    日期
     * @param field   类型 如按秒计算为： Calendar.SECOND
     * @param amount  计算量
     * @return
     * @throws Exception
     * 例子：计算当前时间的前10秒的时间？
     * TimeCalculate(new Date(), Calendar.SECOND, -10)
     * 例子：计算当前时间的后10秒的时间？
     * TimeCalculate(new Date(), Calendar.SECOND, 10)
     */
    public static Date timeCalculate(Date date, int field, int amount) throws Exception {
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(date);
    	cal.add(field, amount);
    	return cal.getTime();
    }
    
    /**
     * long -> date
     * @param l
     * @return
     */
    public static Date long2Date(Long l){
    	Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		return c.getTime();
    }
    
    //TEST
    public static void main(String[] args) {
		try {
			System.err.println(getTimeByDefaultPattern(getNextMonth(new Date())));
			System.err.println(isMidYear(new Date()));
			System.err.println(getAge(parse("2000-01-08"), parse("2016-01-03")));
			System.err.println(getDay(new Date()));
			
			System.err.println(getTimeByCustomPattern(long2Date(System.currentTimeMillis()), defaultTimeStampPattern));
			
		} catch (Exception e) {
			
		}
	}
}
