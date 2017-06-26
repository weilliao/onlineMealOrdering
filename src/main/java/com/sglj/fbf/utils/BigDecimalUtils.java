package com.sglj.fbf.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 
 * @author guanhongwei
 *
 */
public class BigDecimalUtils {

	public static DecimalFormat df = new DecimalFormat("#,##0.00");
	public static DecimalFormat df4 = new DecimalFormat("#,##0.0000");
	
	public static String formatRound2(BigDecimal value){
		return df.format(value);
	}
	
	public static String formatRound4(BigDecimal value){
		return df4.format(value);
	}
	
	public static String formatPercent(BigDecimal value){
//		return df.format(value) + "%";
		return df.format(value);
	}
	
	/**
	 * 保留2位小数，不进行四舍五入
	 * @param value
	 * @return
	 */
	public static BigDecimal withoutRound2(BigDecimal value){
		return value.setScale(2, RoundingMode.FLOOR);
	}
	
	/**
	 * 保留2位小数，进行四舍五入
	 * @param value
	 * @return
	 */
	public static BigDecimal round2(BigDecimal value){
		return value.setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * 除法，结果保留2位小数，不进行四舍五入
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal divideWithoutRound2(BigDecimal divisor, BigDecimal dividend){
		return divisor.divide(dividend, 2, BigDecimal.ROUND_FLOOR);
	}
	
	/**
	 * 除法，结果保留2位小数，进行四舍五入
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal divideRound2(BigDecimal divisor, BigDecimal dividend){
		return divisor.divide(dividend, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 除法，结果保留4位小数，进行四舍五入
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal divideRound4(BigDecimal divisor, BigDecimal dividend){
		return divisor.divide(dividend, 4, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 加法，结果保留2位小数，进行四舍五入
	 * @param addend
	 * @param augend
	 * @return
	 */
	public static BigDecimal addRound2(BigDecimal addend, BigDecimal augend) {
		if(null == addend) addend = new BigDecimal(0);
		if(null == augend) augend = new BigDecimal(0);
		return round2(addend.add(augend));
	}
	
	/**
	 * 减法，结果保留2位小数，进行四舍五入
	 * @param adder
	 * @param addend
	 * @return
	 */
	public static BigDecimal subtractRound2(BigDecimal subtrahend, BigDecimal minuend) {
		if(null == subtrahend) subtrahend = new BigDecimal(0);
		if(null == minuend) minuend = new BigDecimal(0);
		return round2(subtrahend.subtract(minuend));
	}
	
	/**
	 * 判断指定decimal是否大于0
	 * @param decimal
	 * @return
	 */
	public static boolean gtZero(BigDecimal decimal) {
		if(null == decimal) return false;
		BigDecimal zero = new BigDecimal(0);
		return zero.compareTo(decimal) < 0;
	}
	
	/**
	 * 判断指定decimal是否<0
	 * @param decimal
	 * @return
	 */
	public static boolean ltZero(BigDecimal decimal) {
		if(null == decimal) return false;
		BigDecimal zero = new BigDecimal(0);
		return zero.compareTo(decimal) > 0;
	}
	
	/**
	 * 判断指定decimal是否<=0
	 * @param decimal
	 * @return
	 */
	public static boolean leZero(BigDecimal decimal) {
		if(null == decimal) return false;
		BigDecimal zero = new BigDecimal(0);
		return zero.compareTo(decimal) >= 0;
	}
	
	/**
	 * 判断指定decimal是否==0
	 * @param decimal
	 * @return
	 */
	public static boolean eqZero(BigDecimal decimal) {
		if(null == decimal) return false;
		BigDecimal zero = new BigDecimal(0);
		return zero.compareTo(decimal) == 0;
	}
	
	/**
	 * (first > second)
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean gt(BigDecimal first, BigDecimal second) {
		if(null == first) first = new BigDecimal(0);
		if(null == second) second = new BigDecimal(0);
		return first.compareTo(second) > 0;
	}
	
	/**
	 * (first > second)
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean ge(BigDecimal first, BigDecimal second) {
		if(null == first) first = new BigDecimal(0);
		if(null == second) second = new BigDecimal(0);
		return first.compareTo(second) >= 0;
	}
	
	/**
	 * (first < second)
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean lt(BigDecimal first, BigDecimal second) {
		if(null == first) first = new BigDecimal(0);
		if(null == second) second = new BigDecimal(0);
		return first.compareTo(second) < 0;
	}
	
	/**
	 * (first == second)
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean eq(BigDecimal first, BigDecimal second) {
		if(null == first) first = new BigDecimal(0);
		if(null == second) second = new BigDecimal(0);

		return first.compareTo(second) == 0;
	}
	
	/**
	 * BigDecimal转化成String类型,默认精度是2位
	 * @param decimal
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal decimal) {
		return bigDecimalToString(decimal, 2);
	}
	
	/**
	 * BigDecimal转化成String类型
	 * @param decimal
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal decimal, int newScale) {
		if(null == decimal) return "";
		return decimal.setScale(newScale, RoundingMode.HALF_UP).toPlainString();
	}
	
	
	/**
	 * 乘法，结果保留2位小数，进行四舍五入
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static BigDecimal multipRound2(BigDecimal multiplier, BigDecimal multiplicand){
		return multiplier.multiply(multiplicand).setScale(2, RoundingMode.HALF_UP);
	}
	
	public static void main(String[] args){
		System.err.println(BigDecimalUtils.formatPercent(BigDecimal.valueOf(1.45)));
		System.out.println(BigDecimalUtils.round2(BigDecimal.valueOf(1.456789)));
		System.out.println(BigDecimalUtils.round2(BigDecimal.valueOf(1.454789)));
		
		System.out.println(BigDecimalUtils.withoutRound2(BigDecimal.valueOf(1.456789)));
		
		System.out.println(BigDecimalUtils.divideWithoutRound2(BigDecimal.valueOf(30), BigDecimal.valueOf(11)));
		System.out.println(BigDecimalUtils.divideRound2(BigDecimal.valueOf(30), BigDecimal.valueOf(11)));
	}
}
