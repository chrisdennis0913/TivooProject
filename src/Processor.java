import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
	private FinderFactory factory = new FinderFactory(); 
	private FinderInterface wordMethod, timeMethod;
	
	public List<Event> findByKeyWord (List<Event> myEvents, String keyword){
		wordMethod = factory.CreateKeyWordFinder(keyword); 
		return wordMethod.finder(myEvents);
	}
	
	public List<Event> findByTimeFrame (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end){
		timeMethod = factory.CreateTimeFrameFinder(start, end);
		return timeMethod.finder(myEvents);  
	}
	
}
