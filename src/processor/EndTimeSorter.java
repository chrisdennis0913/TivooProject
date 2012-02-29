package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

public class EndTimeSorter implements SorterInterface{

	private boolean ascOrDes; 

	public EndTimeSorter (boolean ascOrDes){
		this.ascOrDes = ascOrDes;
	}


	public List<Event> sorter(List<Event> myEvents) {
		Event[] arrayEvents = myEvents.toArray(new Event[0]);

		for (int i=0; i<= arrayEvents.length-1; i++){
			for (int j=i; j<= arrayEvents.length-1; j++){

				if (((arrayEvents[i].getEndDate().getTimeInMillis() <= arrayEvents[j].getEndDate().getTimeInMillis()) && 
						(ascOrDes)) ||
						((arrayEvents[i].getEndDate().getTimeInMillis() > arrayEvents[j].getEndDate().getTimeInMillis()) && 
						(!ascOrDes) )
						)
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
