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
	
	public static void generateCalendar(String inputFilename)
	{
		BufferedWriter br= null;
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

			br.write("<html>");
			br.newLine();
			br.write("<body>");
			br.newLine();
			br.newLine();

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
				br.write("<h4>"+intToMonth(i)+"</h4>");
				br.newLine();
				br.write("<table border='1'>");
				br.newLine();
				br.write("<tr>");
				br.write("<td>Sun</td><td>Mon</td><td>Tues</td><td>Wed</td>" +
				"<td>Thurs</td><td>Fri</td><td>Sat</td>");
				br.write("</tr>");
				//if statement to determine what first day of month is
				int weekCount = 0;
				int dayCount =0;
				for(int j = 0;j<5;j++)
				{
					br.write("<tr>");
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
						
						br.write("<td>");
						
						
						for(int x = 0;x<thisDay.size();x++)
						{	
							String link = thisDay.get(x).generateDetailsHTML();
							String title = thisDay.get(x).getSubject();
							br.write("<a href=\""+link+"\">"+title+"</a> "+thisDay.get(x).myStart.get(Calendar.HOUR)+":"+thisDay.get(x).myStart.get(Calendar.MINUTE)+":" +
									"-"+thisDay.get(x).myEnd.get(Calendar.HOUR)+":"+thisDay.get(x).myEnd.get(Calendar.MINUTE)+"<br/>");
							
						}						
						br.write("</td>");						
						br.newLine();  
					}	
					br.write("</tr>");
				}
				br.write("</table>");
			}
			br.write("</body>");
			br.write("</html>");
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