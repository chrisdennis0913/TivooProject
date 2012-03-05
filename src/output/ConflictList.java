package output;

import input.Event;

import java.util.GregorianCalendar;
import java.util.List;


public class ConflictList extends Output{
	
	public ConflictList(List<Event> eventList) {
		super(eventList);
		// TODO Auto-generated constructor stub
		myUrl="Conflicts.html";
	}

	public void generate(GregorianCalendar first, GregorianCalendar last)
	{
		try
		{
			// -- not sure exactly how to call this right now but once processor is done should be easy
			//Sorting sort = new Sorting(eventList);
			//sort.sorting(EventList); 
			writer("Conflicts.html");
			
			br.write(header("Conflict List"));
			
			Tag table = new Tag("table","border",1);
			for(Event e: eventList)
			{ 
				start = e.getStartDate();
				end = e.getEndDate();
				table.addInnerHTML(rowCol(start,end,search));
			}

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
