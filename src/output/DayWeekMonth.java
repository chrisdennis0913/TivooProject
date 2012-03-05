package output;

import input.Event;

import java.util.GregorianCalendar;
import java.util.List;


public class DayWeekMonth extends Output{
	
	
	public DayWeekMonth(List<Event> eventList) {
		super(eventList);
		// TODO Auto-generated constructor stub
		myUrl="TimeFrame.html";
	}

	public void generate(GregorianCalendar first, GregorianCalendar last)
	{
		

		try
		{
			
			writer("TimeFrame.html");

			br.write(header("Day, Week or Month"));

			Tag table = new Tag("table","border",1);
			table.addInnerHTML(rowCol(first,last,search));

			br.write(table.getHTML());
			close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
