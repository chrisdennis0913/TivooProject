package processor;

import java.util.GregorianCalendar;
import java.util.List;


public class FinderFactory {
	public KeyWordFinder CreateKeyWordFinder (List<String> keyword, ProcessParameters params){
		return new KeyWordFinder(keyword, params); 
	}
	
	public TimeFrameFinder CreateTimeFrameFinder (GregorianCalendar start, GregorianCalendar end, ProcessParameters params){
		return new TimeFrameFinder(start, end, params); 
	}

}
