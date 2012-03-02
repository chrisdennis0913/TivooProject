package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class TimeSorter implements SearchInterface{
	private boolean ascOrDes; 
	protected abstract boolean checkDate(Event anEvent, Event secEvent, boolean ascOrDes);
	
	protected TimeSorter (boolean ascOrDes){
		this.ascOrDes = ascOrDes; 
	}
	
	public List<Event> search (List<Event> myEvents) {
		Event[] arrayEvents = myEvents.toArray(new Event[0]);

		for (int i=0; i<= arrayEvents.length-1; i++){
			for (int j=i; j<= arrayEvents.length-1; j++){

				if (checkDate(arrayEvents[i], arrayEvents[j], ascOrDes))
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
