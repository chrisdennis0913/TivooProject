package processor;
import input.DukeBasketBallParser;
import input.Event;
import input.InputParser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Processor {	
	private List<FinderInterface> findMethod = new ArrayList<FinderInterface>();
	private List<SorterInterface> sortMethod = new ArrayList<SorterInterface>();
	private List<Event> events;
	
	public Processor (List<Event> myEvents){
		events = myEvents;
	}
	
	public void addSorter (SorterInterface sort){
		sortMethod.add(sort);
	}
	
	public void addFinder (FinderInterface find){
		findMethod.add(find);
	}
	
	public List<Event> process (){
		List<Event> tempEvents = events; 
		for (FinderInterface f: findMethod){
			tempEvents = f.finder(tempEvents);
		}	
		for (SorterInterface s: sortMethod){
			tempEvents = s.sorter(tempEvents);
		}
		return tempEvents;
	}
	
	public static void main (String args[]){
		InputParser par = new DukeBasketBallParser();

		Processor process = new Processor (par.getListOfEvents());		
		List <String> s = new ArrayList<String> ();
		s.add("Boston"); s.add("Tennessee"); s.add("Michigan");
		
		process.addFinder(new KeyWordFinder (s, false));
		process.addSorter(new NameSorter (false)); 
		
		List<Event> ev = process.process(); 
		for (Event e: ev){
			System.out.println (e.toString());
		}
		
	}
}
