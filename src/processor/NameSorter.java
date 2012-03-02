package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

public class NameSorter implements SearchInterface{


	boolean ascOrDes; 

	public NameSorter(boolean ascOrDes) {
		this.ascOrDes = ascOrDes;
	}

	public List<Event> search (List<Event> myEvents) {

		Event[] arrayEvents = myEvents.toArray(new Event[0]);

		for (int i=0; i<=arrayEvents.length-1; i++){	
			for (int j=i; j<=arrayEvents.length-1; j++){
				if ( ((ascOrDes) && (arrayEvents[i].getSubject().compareTo(arrayEvents[j].getSubject())) > 0) ||
				 ((!ascOrDes) && (arrayEvents[i].getSubject().compareTo(arrayEvents[j].getSubject())) < 0) )
				{
					Event temp = arrayEvents[i];
					arrayEvents[i] = arrayEvents[j];
					arrayEvents[j] = temp; 
				}
			}
		}
		
		List<Event> returnEvent = new ArrayList<Event>(); 
		for (int k=0; k<=arrayEvents.length-1; k++){
			returnEvent.add(arrayEvents[k]);
		}

		return returnEvent;
	}
}
