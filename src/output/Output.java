package output;

import input.*;

import processor.*;


import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Output 
{

	List<Event> eventList;
	public Output (List<Event> eventList)
	{
		this.eventList = eventList;
	}

	public void dayWeekMonth(GregorianCalendar first, GregorianCalendar last)
	{
		BufferedWriter br = null;

		try
		{
			
			File file = new File("TimeFrame.html");
			FileWriter fw = new FileWriter(file);

			br = new BufferedWriter(fw);

			br.write(Output.startCal());

			//header tag
			Tag header = new Tag("header");
			header.addInnerHTML("Day Week or Month");
			br.write(header.getHTML());
			
			
			Tag table = new Tag("table","border",1);
				Tag event = new Tag("tr","height",100);
				TimeFrameFinder frame = new TimeFrameFinder(first,last);
				List<Event> window = frame.finder(eventList);
				for (Event d: window)
				{
					Tag col = new Tag("td","width",250);

					String link = d.generateDetailsHTML();
					String title = d.getSubject();
					
					col.addInnerHTML("<a href=\""+link+"\">"+title+"</a> ");	
					event.addInnerHTML(col);
				}
				table.addInnerHTML(event);
				
			

			br.write(table.getHTML());

			br.write(Output.endCal());
			br.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void conflictList()
	{
		BufferedWriter br = null;

		try
		{
			//Processor.sort(EventList) if it needs to be in this class
			File file = new File("Conflicts.html");
			FileWriter fw = new FileWriter(file);

			br = new BufferedWriter(fw);

			br.write(Output.startCal());

			//header tag
			Tag header = new Tag("header");
			header.addInnerHTML("Conflicts list");
			br.write(header.getHTML());
			
			
			Tag table = new Tag("table","border",1);
			for(Event e: eventList)
			{ 
				Tag event = new Tag("tr","height",100);
				TimeFrameFinder day = new TimeFrameFinder(e.getStartDate(),e.getEndDate());
				List<Event> conflicts = day.finder(eventList);
				for (Event d: conflicts)
				{
					Tag col = new Tag("td","width",250);

					String link = d.generateDetailsHTML();
					String title = d.getSubject();
					
					col.addInnerHTML("<a href=\""+link+"\">"+title+"</a> ");	
					event.addInnerHTML(col);
				}
				table.addInnerHTML(event);
				
			}

			br.write(table.getHTML());

			br.write(Output.endCal());
			br.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void sortedList()
	{
		BufferedWriter br = null;

		try
		{
			//Processor.sort(EventList) if it needs to be in this class
			File file = new File("List.html");
			FileWriter fw = new FileWriter(file);

			br = new BufferedWriter(fw);

			br.write(Output.startCal());

			//header tag
			Tag header = new Tag("header");
			header.addInnerHTML("Sorted Event list");
			br.write(header.getHTML());

			Tag table = new Tag("table","border",1);
			for(Event e: eventList)
			{
				Tag event = new Tag("tr","height",100);
				Tag col = new Tag("td","width",250);

				String link = e.generateDetailsHTML();
				String title = e.getSubject();

				col.addInnerHTML("<a href=\""+link+"\">"+title+"</a> ");	
				event.addInnerHTML(col);
				table.addInnerHTML(event);
			}

			br.write(table.getHTML());

			br.write(Output.endCal());
			br.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void generateCalendar()
	{
		BufferedWriter br = null;

		try
		{
			File file = new File("Calendar.html");
			FileWriter fw = new FileWriter(file);
			/* You can also give the path as C:\\Desktop\\fileWriter.txt */
			br = new BufferedWriter(fw);

			br.write(Output.startCal());

			//find number of months in xml file
			int numMonths = 12;
			eventList.get(0).myStart.get(Calendar.MONTH);


			for(int i=0;i<numMonths;i++)
			{
				//should filter into the first month of dates
				//ArrayList<Event> thisMonth = (ArrayList<Event>) process.timeFrameFinder(eventList,
				//		new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+1, 0,eventList.get(0).myStart.get(Calendar.YEAR)),
				//				new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+2, 31,eventList.get(0).myStart.get(Calendar.YEAR)));

				List<Event> thisMonth = new ArrayList<Event>();
				for(Event e : eventList){					
					if(e.getStartDate().get(Calendar.MONTH) == i) thisMonth.add(e);
					//System.out.println(e.getStartDate().get(Calendar.MONTH));
				}


				//if(thisMonth.size() ==0) continue;
				//thisMonth.get(i).myStart.get(Calendar.MONTH)

				//header tag
				Tag header = new Tag("header");
				header.addInnerHTML(Output.intToMonth(i));
				br.write(header.getHTML());

				Tag table = new Tag("table","border",1);
				Tag weeks = new Tag("tr");
				weeks.addInnerHTML("<td>Sun</td><td>Mon</td><td>Tues</td><td>Wed</td><td>Thurs</td><td>Fri</td><td>Sat</td>");
				table.addInnerHTML(weeks);

				int weekCount = 0;
				int dayCount =0;
				for(int j = 0;j<5;j++)
				{
					Tag row = new Tag("tr","height",100);
					//determine how many to skip based off of day of week; possibly do another timeframefinder

					//List<Event> thisWeek =  process.timeFrameFinder(thisMonth,
					//		new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+1, 1+weekCount,eventList.get(0).myStart.get(Calendar.YEAR)),
					//				new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+2, 7+weekCount,eventList.get(0).myStart.get(Calendar.YEAR)));//but j cant be int

					List<Event> thisWeek = new ArrayList<Event>();
					for(Event e : thisMonth){	
						int week = e.getStartDate().get(Calendar.WEEK_OF_MONTH);
						if(week == j) 
							thisWeek.add(e);
					}

					weekCount+=7;

					for(int k = 0;k<7;k++)
					{
						//List<Event> thisDay =  process.timeFrameFinder(eventList,new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+1, 1+dayCount,eventList.get(0).myStart.get(Calendar.YEAR)),
						//		new GregorianCalendar(eventList.get(0).myStart.get(Calendar.MONTH)+i+2, 1+dayCount,eventList.get(0).myStart.get(Calendar.YEAR)));//but j cant be int
						List<Event> thisDay = new ArrayList<Event>();
						for(Event e : thisWeek){	
							int day = e.getStartDate().get(Calendar.DAY_OF_WEEK);
							if(day == k+1) //1 is sunday so you want to check 1 - 7 
								thisDay.add(e);
						}

						dayCount+=1;
						Tag col = new Tag("td","width",250);				

						for(int x = 0;x<thisDay.size();x++)
						{	
							String link = thisDay.get(x).generateDetailsHTML();
							String title = thisDay.get(x).getSubject();
							col.addInnerHTML("<a href=\""+link+"\">"+title+"</a> "+thisDay.get(x).myStart.get(Calendar.HOUR)+":"+
									thisDay.get(x).myStart.get(Calendar.MINUTE) +" - "+
									thisDay.get(x).myEnd.get(Calendar.HOUR)+":"+
									thisDay.get(x).myEnd.get(Calendar.MINUTE)+"<br/>");							
						}
						if (col.getHTML() != null)
							row.addInnerHTML(col);
					}
					if (row.getHTML() != null)
						table.addInnerHTML(row);
				}
				br.write(table.getHTML());

			}

			br.write(Output.endCal());
			br.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

	}
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