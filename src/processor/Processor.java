package processor;
import input.DukeBasketBallParser;
import input.Event;
import input.InputParser;

import java.util.ArrayList;
import java.util.List;


public class Processor {	
	private List<SearchInterface> searchMethod = new ArrayList<SearchInterface>();
	private List<Event> events;
	
	public Processor (List<Event> myEvents){
		events = myEvents;
	}
	
	public void addSorter (SearchInterface sort){
		searchMethod.add(sort);
	}
	
	public void addFinder (SearchInterface find){
		searchMethod.add(find);
	}
	
	public List<Event> process (){
		List<Event> tempEvents = events; 
		for (SearchInterface f: searchMethod){
			tempEvents = f.search(tempEvents);
		}	
		for (SearchInterface s: searchMethod){
			tempEvents = s.search(tempEvents);
		}
		return tempEvents;
	}
	
	public static void main (String args[]){
		InputParser par = new DukeBasketBallParser();

		Processor process = new Processor (par.getListOfEvents());		
		List <String> s = new ArrayList<String> ();
		s.add("Tennessee"); s.add("Michigan");
		List<String> s2 = new ArrayList<String> ();
		s2.add("Western"); 
		
		process.addFinder(new KeyWordFinder (s, false)); //Include or exclude part
		process.addFinder (new KeyWordFinder (s2, false));//This is the "And" part
		process.addSorter(new NameSorter (false)); //This changes the ascending or descending part
		
		List<Event> ev = process.process(); 
		for (Event e: ev){
			System.out.println (e.toString());
		}
		
	}
}
