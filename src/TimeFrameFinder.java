import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class TimeFrameFinder {
	public List<Event> timeFrameFinder (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end){
		ArrayList<Event> timeList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			if ( (e.getStartDate().getTimeInMillis() >= start.getTimeInMillis()) && 
					(e.getStartDate().getTimeInMillis() <= end.getTimeInMillis()) )
				timeList.add(e);
		}
		return timeList;
	}

}
