package processor;

import input.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class TimeSorter implements SorterInterface{
	
	boolean start, ascend; 
	public TimeSorter (boolean myStart, boolean myAscend){
		start = myStart;
		ascend = myAscend;
	}

	@Override
	public List<Event> sorter(List<Event> myEvents) {
		TreeMap<GregorianCalendar, Event> timeSort = new TreeMap<GregorianCalendar, Event>();
		
			for (Event e: myEvents){
				if (start)	
					timeSort.put(e.myStart, e);
				else
					timeSort.put(e.myEnd, e);
			}
			ArrayList<Event> returnEvent = new ArrayList<Event> ();
			Set<GregorianCalendar> detailkey = timeSort.keySet();
			
				for (GregorianCalendar g: detailkey){
					returnEvent.add(timeSort.get(g));
				
				}
			
			
			return returnEvent;
	}
}
