package com.krm.report.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HandelReportDateUtil {
	/**
	 * 获取一段时间内的每一天
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDate(String today,String passday){
	    List<String> listDate = new ArrayList<String>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try{
	        Date startDate = dateFormat.parse(passday);
	        Date endDate = dateFormat.parse(today);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(startDate);
	        while(calendar.getTime().before(endDate)){
//	            System.out.println(dateFormat.format(calendar.getTime()));
	            listDate.add(dateFormat.format(calendar.getTime()));
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	        }
	        listDate.add(today);
	        return listDate;
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    return null;
	}
	/**
	 * 获取一段时间内的每一月
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateByMonth(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
	            System.out.println(dateFormat.format(calendar.getTime()));
				listDate.add(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.MONTH, 1);
			}
			listDate.add(dateFormat.format(calendar.getTime()));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一段时间内的每一季
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateBySeason(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
//				System.out.println(dateFormat.format(calendar.getTime()));
				 int month = calendar.get(Calendar.MONTH) + 1;
				 int season = 0;
				if(month == 1 || month ==2 || month ==3){
//		            System.out.println(calendar.get(Calendar.YEAR)+"1");
		            season=1;
		        }
		        else if(month == 4 || month ==5 || month ==6){
//		            System.out.println(calendar.get(Calendar.YEAR)+"2");
		            season=2;
		        }
		        else if(month == 7 || month ==8 || month ==9){
//		            System.out.println(calendar.get(Calendar.YEAR)+"3");
		            season=3;
		        }
		        else if(month == 10 || month ==11 || month ==12){
//		            System.out.println(calendar.get(Calendar.YEAR)+"4");
		            season=4;
		        }
				listDate.add(new StringBuffer().append(calendar.get(Calendar.YEAR)).append(season).toString());
				calendar.add(Calendar.MONTH, 3);
			}
//			listDate.add(dateFormat.format(calendar.getTime()));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一段时间内的每一季
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateByTenDays(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
//				System.out.println(dateFormat.format(calendar.getTime()));
				for(int i=1;i<=3;i++){
					listDate.add(new StringBuffer().append(dateFormat.format(calendar.getTime())).append("-").append(i).toString());
				}
				calendar.add(Calendar.MONTH, 1);
			}
//			listDate.add(dateFormat.format(calendar.getTime()));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一段时间内的每一年
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateByYear(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
//				System.out.println(dateFormat.format(calendar.getTime()));
				listDate.add(dateFormat.format(calendar.getTime()));
				calendar.add(Calendar.YEAR, 1);
			}
			listDate.add(dateFormat.format(calendar.getTime()));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一段时间内的每一年
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateByHalfYear(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
//				System.out.println(dateFormat.format(calendar.getTime()));
				int month = calendar.get(Calendar.MONTH) + 1;
				 int season = 0;
				if(month >= 1 && month <=6){
		            season=5;
		        }
				if(month >= 7 && month <=12){
					season=6;
				}
				listDate.add(new StringBuffer().append(calendar.get(Calendar.YEAR)).append(season).toString());
				calendar.add(Calendar.MONTH, 6);
			}
//			listDate.add(dateFormat.format(calendar.getTime()));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取一段时间内的每月月初和月末
	 * @param today 结束日期 
	 * @param passday 开始日期
	 * @return
	 */
	public static List<String> handleCirculationDateByMonthBegin(String today,String passday){
		List<String> listDate = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date startDate = dateFormat.parse(passday);
			Date endDate = dateFormat.parse(today);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while(calendar.getTime().before(endDate)){
//				System.out.println(dateFormat.format(calendar.getTime()));
				listDate.add(getCalenderFirstDay(calendar)+"|"+getCalenderLastDay(calendar));
				calendar.add(Calendar.MONTH, 1);
			}
			listDate.add(getCalenderFirstDay(calendar)+"|"+getCalenderLastDay(calendar));
			return listDate;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据日期获取季
	 * @param date
	 * @return
	 */
	public static String getSeason(String date){
		String season = date.substring(4);
		if(season.equals("1")){
			return date.substring(0,4)+" 第一季度";
		}
		if(season.equals("2")){
			return date.substring(0,4)+" 第二季度";
		}
		if(season.equals("3")){
			return date.substring(0,4)+" 第三季度";
		}
		if(season.equals("4")){
			return date.substring(0,4)+" 第四季度";
		}
		return "";
	}
	/**
	 * 根据日期获取季
	 * @param date
	 * @return
	 */
	public static String getHalfYear(String date){
		String season = date.substring(4);
		if(season.equals("5")){
			return date.substring(0,4)+" 上半年";
		}
		if(season.equals("6")){
			return date.substring(0,4)+" 下半年";
		}
		return "";
	}
	
	public static String getCalenderLastDay(Calendar c){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
		String last = format.format(c.getTime());
		return last;
	}
	public static String getCalenderFirstDay(Calendar c){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		String last = format.format(c.getTime());
		return last;
	}
	
	public static String getCalenderTenDays(String date){
		String tenDays = date.substring(date.lastIndexOf("-")+1);
		if(tenDays.equals("1")){
			return date.substring(0, date.lastIndexOf("-"))+" 上旬";
		}
		if(tenDays.equals("2")){
			return date.substring(0, date.lastIndexOf("-"))+" 中旬";
		}
		if(tenDays.equals("3")){
			return date.substring(0, date.lastIndexOf("-"))+" 下旬";
		}
		return "";
	}
	public static void main(String[] args) {
//		List<String> listDate = HandelReportDateUtil.handleCirculationDate("2018-07-10", "2019-05-10");
//		List<String> listDate = DayReportDateUtil.handleCirculationDateByMonth("2018-07-09", "2018-05-03");
//		List<String> listDate = DayReportDateUtil.handleCirculationDateBySeason("2018-8-09", "2017-03-03");
//		List<String> listDate = DayReportDateUtil.handleCirculationDateByYear("2018-8-09", "2017-03-03");
		List<String> listDate = HandelReportDateUtil.handleCirculationDateByMonthBegin("2018-12-31", "2018-03-04");
//		List<String> listDate = DayReportDateUtil.handleCirculationDateByHalfYear("2019-01-09", "2018-06-01");
//		List<String> listDate = DayReportDateUtil.handleCirculationDateByTenDays("2018-09-01", "2018-06-01");
//		System.out.println(listDate.size());
		System.out.println(listDate);
//		String seasinString = getSeason("20182");
//		System.out.println(seasinString);
		
//		String tend = getCalenderTenDays("2018-06-2");
//		System.out.println(tend);
	}
}
