import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class TimeFrameFinder implements FinderInterface{
	
	private GregorianCalendar startTime, endTime;
	public TimeFrameFinder (GregorianCalendar start, GregorianCalendar end){
		startTime = start;
		endTime = end;
	}
	
	public List<Event> finder (List<Event> myEvents){
		ArrayList<Event> timeList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			if ( (e.getStartDate().getTimeInMillis() >= startTime.getTimeInMillis()) && 
					(e.getStartDate().getTimeInMillis() <= endTime.getTimeInMillis()) )
				timeList.add(e);
		}
		return timeList;
	}

}
