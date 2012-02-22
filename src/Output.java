import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

class Output 
{
	public static String startCal()
	{
		return "<html> \n <body> \n\n\n";
	}
	
	public static String endCal()
	{
		return "</body> \n </html>";
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
	
	public static void generateCalendar(String inputFilename)
	{
		BufferedWriter br = null;
		
		try
		{
			File file = new File("Calendar.html");
			FileWriter fw = new FileWriter(file);
			/* You can also give the path as C:\\Desktop\\fileWriter.txt */
			br = new BufferedWriter(fw);

			InputParser input = InputParser.ParserFactory.generate(inputFilename);
            List<Event> eventList = input.getListOfEvents();
            Collections.sort(eventList);
			
			Processor process = new Processor();

			br.write(startCal());

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
					System.out.println(e.getStartDate().get(Calendar.MONTH));
				}
				
				
				//if(thisMonth.size() ==0) continue;
				//thisMonth.get(i).myStart.get(Calendar.MONTH)
				br.write(createHeader(intToMonth(i)));
				br.write(createTable(1));
				br.write(createRow(100));
				br.write("<td>Sun</td><td>Mon</td><td>Tues</td><td>Wed</td>" +
				"<td>Thurs</td><td>Fri</td><td>Sat</td>");
				br.write(endRow());
				//if statement to determine what first day of month is
				int weekCount = 0;
				int dayCount =0;
				for(int j = 0;j<5;j++)
				{
					br.write(createRow(100));
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
						br.write(createCol(250));					
						
						for(int x = 0;x<thisDay.size();x++)
						{	
							String link = thisDay.get(x).generateDetailsHTML();
							String title = thisDay.get(x).getSubject();
							br.write(addEvent(link,title,thisDay,x));
							
						}						
						br.write(endCol());						
						br.newLine();  
					}	
					br.write(endRow());
				}
				br.write(endTable());
			}
			br.write(endCal());
			br.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static String intToMonth(int i){
		String[] months = {"January","February","March","April","May", 
				"June", "July","August","September", "October", "November", "December"};
		return months[i];
		
	}

} 