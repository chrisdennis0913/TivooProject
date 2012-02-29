package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

public class TimeSorter implements SorterInterface{

	private int sortmethod; 
	private boolean ascOrDes; 

	public TimeSorter (ProcessParameters param){
		sortmethod = param.getSortMethod();
		ascOrDes = param.getAscOrDes();
	}


	public List<Event> sorter(List<Event> myEvents) {
		Event[] arrayEvents = myEvents.toArray(new Event[0]);

		for (int i=0; i<= arrayEvents.length-1; i++){
			for (int j=i; j<= arrayEvents.length-1; j++){

				if ( ((arrayEvents[i].getStartDate().getTimeInMillis() >= arrayEvents[j].getStartDate().getTimeInMillis()) && 
						(ascOrDes) && (sortmethod == ProcessParameters.SORT_BY_STARTTIME)) || 
						((arrayEvents[i].getStartDate().getTimeInMillis() < arrayEvents[j].getStartDate().getTimeInMillis()) && 
						(!ascOrDes) && (sortmethod == ProcessParameters.SORT_BY_STARTTIME)) ||

						((arrayEvents[i].getEndDate().getTimeInMillis() <= arrayEvents[j].getEndDate().getTimeInMillis()) && 
						(ascOrDes) && (sortmethod == ProcessParameters.SORT_BY_ENDTIME)) ||
						((arrayEvents[i].getEndDate().getTimeInMillis() > arrayEvents[j].getEndDate().getTimeInMillis()) && 
						(!ascOrDes) && (sortmethod == ProcessParameters.SORT_BY_ENDTIME))
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
