package output;

import input.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Output 
{
	
	public static String startCal()
	{
		return "<html> \n <body> \n\n\n";
	}
	
	public static String endCal()
	{
		return "</body> \n </html>";
	}
	
	static String[] dayList = {"<td>Sun</td>/n","<td>Mon</td>/n", "<td>Tues</td>/n", "<td>Wed</td>/n", 
		"<td>Thurs</td>/n", "<td>Fri</td>/n", "<td>Sat</td>/n"};
	static List<String> days = Arrays.asList(dayList);
	
	public static List<String> daysOfWeek()
	{
		return days;
	}
	
	public static String createHeader(String header)
	{
		return "<h4>"+header+"</h4> \n";
	}
	
	public static String createTable(int border)
	{
		return "<table border='"+border+"'> \n";
	}
	
	public static String endTable()
	{
		return "</table>";
	}
	
	public static String createRow(int height)
	{
		return "<tr style=\"height:"+height+"px;\">";
	}
	
	public static String endRow()
	{
		return "</tr>";
	}
	
	public static String createCol(int width)
	{
		return "<td style=\"width:"+width+"px\">";
	}
	
	public static String endCol()
	{
		return "</td>";
	}
	
	public static String addEvent(String link, String title, List<Event> thisDay, int x)
	{
		return "<a href=\""+link+"\">"+title+"</a> "+thisDay.get(x).myStart.get(Calendar.HOUR)+":"+
					thisDay.get(x).myStart.get(Calendar.MINUTE) +" - "+
					thisDay.get(x).myEnd.get(Calendar.HOUR)+":"+thisDay.get(x).myEnd.get(Calendar.MINUTE)+"<br/>";
	}
	
	public static String intToMonth(int i){
		String[] months = {"January","February","March","April","May", 
				"June", "July","August","September", "October", "November", "December"};
		return months[i];
		
	}

} 