package processor;

import input.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class NameSorter implements SorterInterface{

	
	boolean ascend; 
	public NameSorter (boolean myAscend){
		ascend = myAscend;
	}

	public List<Event> sorter (List<Event> myEvents) {
		TreeMap<String, Event> nameSort = new TreeMap<String, Event>();
		
			for (Event e: myEvents){	
				nameSort.put(e.mySubject, e);
			}
	
			ArrayList<Event> returnEvent = new ArrayList<Event> ();
			
			Set<String> detailkey = nameSort.keySet();
			for (String s: detailkey){
					returnEvent.add(nameSort.get(s));
			}
			
			return returnEvent;
	}
}
