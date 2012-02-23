package processor;
import input.Event;

import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
	private FinderFactory factory = new FinderFactory(); 
	private KeyWordFinder keywordF; 
	private TimeFrameFinder timeframeF; 
	
	public List<Event> findByKeyWord (List<Event> myEvents, String keyword){
		if (keywordF==null)
			keywordF = factory.CreateKeyWordFinder(); 
		
		return keywordF.keywordFinder(myEvents, keyword);  
	}
	
	public List<Event> findByKeyWord (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end){
		if (timeframeF==null)
			timeframeF = factory.CreateTimeFrameFinder();
		
		return timeframeF.timeFrameFinder(myEvents, start, end);  
	}
	
	
}
