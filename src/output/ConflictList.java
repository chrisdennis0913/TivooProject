package output;

import input.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.List;

import processor.TimeFrameFinder;

public class ConflictList extends Output{
	
	public ConflictList(List<Event> eventList) {
		super(eventList);
		// TODO Auto-generated constructor stub
	}

	public void generate(GregorianCalendar first, GregorianCalendar last)
	{
		

		try
		{
			// -- not sure exactly how to call this right now but once processor is done should be easy
			//Sorting sort = new Sorting(eventList);
			//sort.sorting(EventList); 
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
				TimeFrameFinder day = new TimeFrameFinder(e.getStartDate(),e.getEndDate(), true);

				List<Event> conflicts = day.search(eventList);
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
}
