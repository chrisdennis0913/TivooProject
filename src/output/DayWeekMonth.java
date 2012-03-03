package output;

import input.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;
import java.util.List;

import processor.TimeFrameFinder;

public class DayWeekMonth extends Output{
	
	
	public DayWeekMonth(List<Event> eventList) {
		super(eventList);
		// TODO Auto-generated constructor stub
	}

	public void generate(GregorianCalendar first, GregorianCalendar last)
	{
		

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
			TimeFrameFinder frame = new TimeFrameFinder(first,last, true);

			List<Event> window = frame.search(eventList);
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
}
