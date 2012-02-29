package processor;
import input.DukeBasketBallParser;
import input.Event;
import input.InputParser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
	private FinderFactory factory = new FinderFactory(); 
	private SorterFactory sfactory = new SorterFactory();
	
	private FinderInterface wordMethod, timeMethod;
	private SorterInterface sortMethod;
	
	public List<Event> findByKeyWord (List<Event> myEvents, List<String> keyword, ProcessParameters params){
		wordMethod = factory.CreateKeyWordFinder(keyword, params);
		
		if (params.getSortMethod() == ProcessParameters.SORT_BY_NAME)
			sortMethod = sfactory.CreateNameSorter(params);
		else
			sortMethod = sfactory.CreateTimeSorter(params);
		
		return sortMethod.sorter(wordMethod.finder(myEvents));
	}
	
	public List<Event> findByTimeFrame (List<Event> myEvents, GregorianCalendar start, GregorianCalendar end, 
			ProcessParameters params){
		timeMethod = factory.CreateTimeFrameFinder(start, end, params);
				
		if (params.getSortMethod() == ProcessParameters.SORT_BY_NAME)
			sortMethod = sfactory.CreateNameSorter(params);
		else
			sortMethod = sfactory.CreateTimeSorter(params);
		
		return sortMethod.sorter(timeMethod.finder(myEvents));
	}
	
	
	public static void main (String args[]){
		InputParser par = new DukeBasketBallParser();
		
		Processor process = new Processor ();
		List <String> s = new ArrayList<String> ();
		s.add("Boston"); s.add("Tennessee"); s.add("Michigan");
		ProcessParameters p = new ProcessParameters(false, true, ProcessParameters.SORT_BY_NAME);
		List <Event> e = process.findByKeyWord(par.getListOfEvents(), s, p);
		
		for (Event ev: e){
			System.out.println (ev.toString());
		}
		
	}
}
