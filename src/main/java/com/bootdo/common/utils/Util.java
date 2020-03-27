package com.bootdo.common.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util {
	private static Log log = LogFactory.getLog(Util.class);

	public static int MAX_RECORD_COUNT = 2000;
	public static String MAX_RECORD_COUNT_MSG = " 2000 ";

	public static boolean isNullOrEmpty(String inStr) {
		return (inStr == null || inStr.trim().length() == 0);
	}
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj instanceof Object[]) {
			Object[] o = (Object[]) obj;
			for (int i = 0; i < o.length; i++) {
				Object object = o[i];
				if (object instanceof Date) {
					if (object.equals(new Date(0)))
						return true;
				} else if ((object == null) || (("").equals(object))) {
					return true;
				}
			}
		} else {
			if (obj instanceof Date) {
				if (obj.equals(new Date(0))) {
					return true;
				}
			} else if(obj instanceof Map) {
				if(obj == null || ((Map)obj).isEmpty()) {
					return true;
				}
			} else if(obj instanceof List) {
				if(obj == null || ((List)obj).isEmpty()) {
					return true;
				}
			}else if ((obj == null) || (("").equals(obj))) {
				return true;
			} 
		}

		return false;
	}
	/**
	 * 根据当前日期计算出与当前日期间隔时间单位的日期
	 * 
	 * @param currentDate
	 *            当前日期
	 * @param dateUnit
	 *            时间单位
	 * @param prev
	 *            前滚标志
	 * @param dateUnitType
	 *            滚动日期单位的类型
	 * @return
	 */
	public static Date rollDateByDateUnit(Date currentDate, int dateUnit, int dateUnitType) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(dateUnitType, dateUnit);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 根据当前日期计算出与当前日期间隔天数的日期
	 * 
	 * @param dateTime
	 * @param value
	 * @return
	 */
	public static Date rollDateByDay(Date currentDate, int day) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(Calendar.DATE, day);
		return new Date(calendar.getTime().getTime());
	}

	public static Date rollDateByWeek(Date currentDate, int week) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(Calendar.WEDNESDAY, week);
		return new Date(calendar.getTime().getTime());
	}

	public static Date rollDateByMonth(Date currentDate, int month) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(Calendar.MONTH, month);
		return new Date(calendar.getTime().getTime());
	}

	public static Date rollDateByYear(Date currentDate, int year) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (currentDate != null) {
			calendar.setTime(currentDate);
		}
		calendar.add(Calendar.YEAR, year);
		return new Date(calendar.getTime().getTime());
	}

	public static Date toDate(int year, int month, int day) {
		return toDate(year, month, day, 0, 0, 0);
	}

	public static Date toDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day, hour, minute, second);
		return new Date(c.getTimeInMillis());
	}

	private static int toDays(Date date) {
		int dayOffset = -1;
		Calendar c = Calendar.getInstance();
		Calendar timeZero = Calendar.getInstance();
		timeZero.setTimeInMillis(0);
		c.setTime(date);

		while (timeZero.get(Calendar.YEAR) < c.get(Calendar.YEAR)) {
			dayOffset += timeZero.getActualMaximum(Calendar.DAY_OF_YEAR);
			timeZero.add(Calendar.YEAR, 1);
		}

		dayOffset += c.get(Calendar.DAY_OF_YEAR);
		return dayOffset;
	}

	public int differByDay(Date date1, Date date2) {
		return (int) (toDays(date1) - toDays(date2));
	}

	public static boolean isBoolean(String s) {
		if (("Y").equals(s) || (("Yes").equals(s))) {
			return true;
		}
		return false;
	}

	
	public static int resovleDateUnit(String unit) {
		if (unit.equals(DAY)) {
			return Calendar.DATE;
		} else if (unit.endsWith(MONTH)) {
			return Calendar.MONTH;
		} else if (unit.endsWith(YEAR)) {
			return Calendar.YEAR;
		} else if (unit.endsWith(WEEK)) {
			return Calendar.WEEK_OF_YEAR;
		} else {
			throw new IllegalArgumentException("invalid data parameter " + unit);
		}
	}

	private final static String DAY = "D";
	private final static String MONTH = "M";
	private final static String YEAR = "Y";
	private final static String WEEK = "W";

	

	/**
	 * 将BASE64编码的字符串进行解析为原始数据对象
	 * 
	 * @param encodeBase64
	 * @return
	 */
	public static Object decoderBASE64ToString(String encoderBase64) {
		return null;
		// return new String(Base64.base64ToByteArray(encoderBase64));
	}

	/**
	 * 将string格式的日期按照指定的日期格式转换成date类型
	 */
	public static java.util.Date parseDate(String dateString, String pattern) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
		try {
			return (java.util.Date) simpledateformat.parse(dateString);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static boolean equals(Object obj, Object anObject) {
		boolean bool = false;
		if (obj == null || anObject == null) {
			return bool;
		}

		bool = obj.equals(anObject);
		return bool;
	}

	/**
	 * 开通的认证类型:1表示开通,0表示关闭.AuthenticateType:000000 表示:第一位表示交易密码认证 第二位渠道密码认证
	 * 第三位证书认证 第四表示动态口令认证 第五位表示交易短信认证 第六位表示支付密码认证
	 */
	public final static String AuthenticateTypeMapping = "TECOSP";

	/**
	 * 身份证18位转15位
	 * 
	 * @author guojie
	 * 
	 * @param idNo
	 * @return String
	 */
	public static String convertIdNoToShort(String idNo) {
		if (idNo.length() != 18) {
			return "";
		}
		// 去除年份前2位，和最后一位
		return idNo.substring(0, 6) + idNo.substring(8, 17);
	}

	/**
	 * 身份证15位转18位
	 * 
	 * @author guojie
	 * 
	 * @param idNo
	 * @return String
	 */
	public static String convertIdNoToLong(String idNo) {
		if (idNo.length() != 15) {
			return "";
		}
		idNo = idNo.substring(0, 6) + "19" + idNo.substring(6);
		// 加权因子
		int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		// 校验码
		String[] vi = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
		int sum = 0;
		for (int i = 0; i < idNo.length(); i++) {
			sum = sum + wi[i] * Integer.valueOf(idNo.substring(i, i + 1)).intValue();
		}
		int y = sum % 11;
		return idNo + vi[y];
	}

	/**
	 * 
	 * {比较两个日期的大小 .如果date1在date2之后，返回true; date1在date2之前或相等，返回false}
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	public static boolean dateCompare(Date date1, Date date2) {
		return date1.compareTo(date2) > 0 ? true : false;
	}

	/**
	 * 
	 * {身份证15位转18位}
	 * 
	 * @param IdNo
	 * @return
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @note 蓝海银行提供的转换方法
	 */
	public static String convert15to18(String IdNo) {
		String sID15 = IdNo;
		String sID18 = "";
		String sTemp = "";
		if (sID15 == null || sID15.trim().length() != 15) {
			sID18 = "";
		} else {
			sID15 = sID15.trim();
			try {
				sTemp = sID15.substring(0, 6) + "19" + sID15.substring(6);
				sID18 = sTemp + getVerify(sTemp);
			} catch (Exception e) {
				sID18 = "";
			}
		}
		return sID18;
	}

	/**
	 * 
	 * {返回18位最后一位验证码}
	 * 
	 * @param s17
	 * @return
	 * @throws Exception
	 * 
	 * @version 1.0
	 * @since 1.0
	 * @note 蓝海银行提供的转换方法
	 */
	private static String getVerify(String s17) throws Exception {
		String sVerify = "";
		String code = "";
		int num = 0;
		int tmp = 0;
		for (int i = 18; i >= 2; i--) {
			num += (Math.pow(2, i - 1) % 11) * Integer.parseInt(s17.substring(18 - i, 18 - i + 1));
		}
		num %= 11;
		switch (num) {
		case 0:
			code = "1";
			break;
		case 1:
			code = "0";
			break;
		case 2:
			code = "X";
			break;
		default:
			tmp = 12 - num;
			code = Integer.toString(tmp);
			break;
		}
		sVerify = code;
		return sVerify;
	}
	
	/**
	 * 产生n位随机数 STRONG
	 * @param n
	 * @param radix 10-只有数字	36-包含数字和字母(小写)
	 * @return
	 */
	public static String randomPasswordSNK(int n, int radix) {
		SecureRandom a = new SecureRandom();
		long l = a.nextLong();
		long l1 = l < 0L ? -l : l;
		StringBuffer stringbuffer ;
		
		String pre =String.valueOf(new Random().nextInt(10)) ;
		String[] subs = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String sub = subs[new Random().nextInt(26)];
		
		for (stringbuffer = new StringBuffer(Long.toString(l1, radix)); 
				stringbuffer.length() < n; stringbuffer.insert(0, '0'))
			;
		if (stringbuffer.length() > n)
			return stringbuffer.substring(stringbuffer.length() - n).concat(pre).concat(sub);
		else
			return stringbuffer.toString().concat(pre).concat(sub);
	}
	
	/**
	 * 产生n位随机数 STRONG
	 * @param n
	 * @param radix 10-只有数字	36-包含数字和字母(小写)
	 * @return
	 */
	public static String randomPasswordSNK1(String idno) {
		String idnos = idno.substring(idno.length()-4,idno.length());
		StringBuffer stringbuffer = new StringBuffer();
		String pre =String.valueOf(new Random().nextInt(900)+100) ;
		
		String[] subs = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String sub = subs[new Random().nextInt(26)];
		return stringbuffer.append(idnos).append(pre).append(sub).toString();
	}
	
	/**
	 * 产生n位随机数
	 * 
	 * @param n
	 * @param radix
	 *            10-只有数字 36-包含数字和字母(小写)
	 * @return
	 */
	public static String randomPassword(int n, int radix) {
		SecureRandom a = new SecureRandom();
		long l = a.nextLong();
		long l1 = l < 0L ? -l : l;
		StringBuffer stringbuffer;
		for (stringbuffer = new StringBuffer(Long.toString(l1, radix)); stringbuffer.length() < n; stringbuffer
				.insert(0, '0'))
			;
		if (stringbuffer.length() > n)
			return stringbuffer.substring(stringbuffer.length() - n);
		else
			return stringbuffer.toString();
	}


	/**
	 * 组织机构代码格式化
	 * 
	 * @param orgCode
	 * 
	 * @author guojie
	 * 
	 */
	public static String formatOrgCode(String orgCode) {
		if (orgCode == null || orgCode.length() < 9) {
			return orgCode;
		}
		if (orgCode.substring(8, 9).equals("-")) {
			return orgCode;
		}

		return orgCode.substring(0, 8) + "-" + orgCode.substring(8);
	}

	/**
	 * 判断字符串是否是数字串
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num) {
		if (num == null) {
			return false;
		}
		for (int i = 0; i < num.length(); ++i) {
			char c = num.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public static java.util.Date rollDateByMinute(java.util.Date date, int minutes) {
		Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MINUTE, minutes);
		return new java.util.Date(calendar.getTimeInMillis());
	}

	/**
	 * 判断是否为简单密码
	 * 
	 * @author wangqi
	 */
	public static boolean checkSimplePwd(String pwd) {
		char[] charArray = pwd.toCharArray();
		String eq = "";
		String asc = "";
		String desc = "";
		for (int i = 0; i < charArray.length - 1; i++) {
			Character c1 = charArray[i];
			Character c2 = charArray[i + 1];
			if (c1.equals(c2)) {
				eq += "1";
				continue;
			}
			String s = c1.toString() + c2.toString();
			if (Pattern.compile("^\\d+$").matcher(s.toString()).matches()) {
				int i1 = Integer.valueOf(c1.toString());
				int i2 = Integer.valueOf(c2.toString());
				if (i1 - i2 == 1)
					desc += "1";
				else if (i2 - i1 == 1) {
					asc += "1";
				} else
					break;
			} else
				break;
		}
		if (eq.length() == pwd.length() - 1 || desc.length() == pwd.length() - 1 || asc.length() == pwd.length() - 1) {
			return false;
		}
		return true;
	}

	/**
	 * 判断个人USBKey编号有效性
	 * 
	 * @author wangqi
	 */
	public static boolean checkPerUSBNo(String no) {
		if (no.startsWith("CF"))
			return true;
		if (no.length() != 12)
			return false;
		if (!Pattern.compile("^\\d+$").matcher(no).matches())
			return false;
		int checkNo = Integer.valueOf(no.substring(6, 7));
		String realNo = no.substring(0, 6) + no.substring(7);
		char[] c = realNo.toCharArray();
		int count = 0;
		for (int i = c.length - 1; i >= 0; i--) {
			int cc = Integer.valueOf(((Character) c[i]).toString());
			int jj = (c.length - i - 1) % 2 == 0 ? cc * 2 : cc;
			int kk = jj >= 10 ? jj / 10 + jj % 10 : jj;
			count += kk;
		}
		return (10 - count % 10) % 10 == checkNo;
	}

	public static boolean checkEntUSBNo(String no) {
		if (!no.startsWith("C"))
			return true;
		if (!no.startsWith("CO") && !no.startsWith("C0"))
			return false;
		if (no.length() != 14 && no.length() != 13)
			return false;
		String realCompareNo = "";
		if (no.length() == 13)
			realCompareNo = no.substring(1);
		else if (no.length() == 14)
			realCompareNo = no.substring(2);

		if (!Pattern.compile("^\\d+$").matcher(realCompareNo).matches())
			return false;
		int checkNo = Integer.valueOf(realCompareNo.substring(6, 7));
		String realNo = realCompareNo.substring(0, 6) + realCompareNo.substring(7);
		char[] c = realNo.toCharArray();
		int count = 0;
		for (int i = c.length - 1; i >= 0; i--) {
			int cc = Integer.valueOf(((Character) c[i]).toString());
			int jj = (c.length - i - 1) % 2 == 0 ? cc * 2 : cc;
			int kk = jj >= 10 ? jj / 10 + jj % 10 : jj;
			count += kk;
		}
		return (10 - count % 10) % 10 == checkNo;

	}

	/**
	 * 
	 * 获取一个对象的string形式并去除空格
	 * 
	 * @param object
	 * @return
	 * 
	 * @version 1.0
	 * @since 1.0
	 */
	public static String toStringAndTrim(Object object) {
		if (object == null) {
			return "";
		} else {
			return object.toString().trim();
		}

	}

	/**
	 * 字符串替换,用replaceWith替换str中的replaced
	 * 
	 * @param str
	 * @param replaced
	 * @param replaceWith
	 * @return
	 */
	public static String replace(String str, String replaced, String replaceWith) {
		if (str == null) {
			return null;
		}
		if (replaced == null || replaceWith == null) {
			return str;
		}
		StringBuffer buf = new StringBuffer();

		int pos = str.indexOf(replaced);
		if (pos > -1) {
			String left = str.substring(0, pos);
			String right = str.substring(pos + replaced.length());
			if (right.length() < 2) {
				right = right + "0";
			}
			buf.append(left);
			buf.append(replaceWith);
			buf.append(replace(right, replaced, replaceWith));
		} else {
			buf.append(str);
		}

		return buf.toString();
	}

	

	/**
	 * 格式处理 1234567890 变成 1234**7890
	 * 
	 * @param beginIndex
	 *            前面保留几位
	 * @param endIndex
	 *            后面保留几位
	 * @param replace
	 *            替代符号
	 * @param acno
	 * @return
	 */
	public static String acNoFormat(int beginIndex, int endIndex, String replace, String acno) {
		StringBuffer sb = new StringBuffer();
		char[] acNochars = acno.toString().toCharArray();

		for (int i = 0; i < acNochars.length; i++) {
			if (i >= beginIndex && i <= (acNochars.length - beginIndex - 1)) {
				sb.append("*");
			} else {
				sb.append(acNochars[i]);
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * 比较两个对象是否相等
	 * 
	 * @param firstStr
	 * @param secondStr
	 * @return
	 *
	 */

	public static boolean trimAndEquals(Object firstStr, Object secondStr) {
		if (firstStr == null && secondStr == null) {
			return true;
		} else if (firstStr == null || secondStr == null) {
			return false;
		} else {
			return toStringAndTrim(firstStr).equals(toStringAndTrim(secondStr));
		}

	}



	/**
	 * 把object 转换成SqlDate
	 */
	public static Date convertSqlDate(Object s) {
		if (s == null) {
			return null;
		} else {
			if (s instanceof Long) {
				return new Date((Long) s);
			} else if (s instanceof Number) {
				return new Date(((Number) s).longValue());
			} else if (s instanceof String) {
				return Date.valueOf((String) s);
			} else {
				return (Date) s;
			}
		}
	}
	
	/**
	 * 前提条件：list中存储对象为map，sortkey为key中某个值，descend排序方式true：降序排序  false升序；把value对象的tostring（）结果比较；
	 */
	@SuppressWarnings("rawtypes")
	private static class MapSingleComparator implements Comparator{
		Object sortKey;
		boolean descend;
		
		public MapSingleComparator(Object sortKey, boolean descend){
			this.sortKey = sortKey;
			this.descend = descend;
		}
		
		public int compare(Object o1, Object o2){
			int compareResult;
			if(o1 == null && o2 == null)
				compareResult = 0;	
			else if (o1 == null)
				compareResult = -1;
			else if ( o2 == null)
				compareResult =1;
			else {

				Object obj1 = ((Map) o1).get(sortKey);

				Object obj2 = ((Map) o2).get(sortKey);
				if (obj1 == null && obj2 == null)
					compareResult = 0;
				else if (obj1 == null)
					compareResult = -1;
				else if (obj2 == null)
					compareResult = 1;
				else 
					compareResult = obj1.toString().compareTo(obj2.toString());
			}
			return descend ? compareResult * -1 : compareResult;
		}
	}
	
	/**
	 * 将集合dataList按指定的字段进行排序
	 */
	@SuppressWarnings("unchecked")
	public static void sortListBySingleMapKey(@SuppressWarnings("rawtypes") List dataList, Object sortKey, boolean descend){
		if (sortKey == null) {
			log.error("sort keys should not be null.");
			return;
		}
		Collections.sort(dataList, new MapSingleComparator(sortKey, descend));
		return;
	}
	
	/**
	 * 流水号规则处理
	 * @author ckp
	 */
	public static Long getJnlNo(Long jnlno){
		return jnlno;
		/*if (jnlno.toString().length() > 10){
			String str = jnlno.toString().substring(jnlno.toString().length()-10,jnlno.toString().length());
			jnlno = Long.valueOf(str);
		}
		String strJnlno = String.format("%010d", jnlno);
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Long idmcjnl = Long.valueOf((formatter.format(date)+strJnlno).toString()) ;
		return idmcjnl;*/
	}
	
	
	public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();   
    }
	/**
	 * 生成token
	 * @author cz
	 */
	public static String getMakeToken(){
		String taken = "";
		Date date = new Date(System.currentTimeMillis());
		taken = string2MD5(date.getTime()+"");
		return taken;
	}
	
	public static void main(String[] args) {
		System.err.println(getMakeToken());
	}
	
	
}
