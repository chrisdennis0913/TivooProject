package processor;
import input.DukeBasketBallParser;
import input.Event;
import input.InputParser;

import java.util.ArrayList;
import java.util.List;


public class Processor {	
	private List<SearchInterface> findMethod = new ArrayList<SearchInterface>();
	private List<SearchInterface> sortMethod = new ArrayList<SearchInterface>();
	private List<Event> events;

	public Processor (List<Event> myEvents){
		events = myEvents;
	}

	public void addSorter (SearchInterface sort){
		sortMethod.add(sort);
	}

	public void addFinder (SearchInterface find){
		findMethod.add(find);
	}

	public List<Event> process (){
		List<Event> tempEvents = events; 
		for (SearchInterface f: findMethod){
			tempEvents = f.search(tempEvents);
		}	
		for (SearchInterface s: sortMethod){
			tempEvents = s.search(tempEvents);
		}
		return tempEvents;
	}

	public static void main (String args[]){
		InputParser par = new DukeBasketBallParser();

		Processor process = new Processor (par.getListOfEvents());		
		List <String> s = new ArrayList<String> ();
		s.add("Boston"); s.add("Tennessee"); s.add("Michigan");

		process.addFinder(new KeyWordFinder (s, false));
		process.addSorter(new NameSorter (true)); 

		List<Event> ev = process.process(); 
		for (Event e: ev){
			System.out.println (e.toString());
		}

	}
}