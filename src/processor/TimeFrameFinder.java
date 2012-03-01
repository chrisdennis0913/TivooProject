package processor;
import input.Event;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class TimeFrameFinder implements SearchInterface{
	private boolean incOrExc;
	
	private GregorianCalendar startTime, endTime;
	public TimeFrameFinder (GregorianCalendar start, GregorianCalendar end, boolean incOrExc){
		startTime = start;
		endTime = end;
		this.incOrExc = incOrExc;
	}
	
	public List<Event> search (List<Event> myEvents){
		ArrayList<Event> timeList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			if ( (e.getStartDate().getTimeInMillis() >= startTime.getTimeInMillis()) && 
					(e.getStartDate().getTimeInMillis() <= endTime.getTimeInMillis()) && (incOrExc))
				timeList.add(e);
			if ( (e.getStartDate().getTimeInMillis() < startTime.getTimeInMillis()) && 
					(e.getStartDate().getTimeInMillis() > endTime.getTimeInMillis()) && (!incOrExc))
				timeList.add(e);
		}
		return timeList;
	}

}
