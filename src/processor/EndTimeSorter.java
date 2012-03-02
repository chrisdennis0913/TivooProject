package processor;

import input.Event;


public class EndTimeSorter extends TimeSorter{

	public EndTimeSorter (boolean ascOrDes){
		super(ascOrDes);
	}
	protected boolean checkDate(Event anEvent, Event secEvent, boolean ascOrDes) {
		return ( ( (anEvent.getEndDate().getTimeInMillis() <= secEvent.getEndDate().getTimeInMillis() ) && 
				(ascOrDes) ) || 
				(anEvent.getEndDate().getTimeInMillis() > secEvent.getEndDate().getTimeInMillis()) && 
				(!ascOrDes));

	}
}