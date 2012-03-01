package processor;

import input.Event;


public class StartTimeSorter extends TimeSorter{

	public StartTimeSorter (boolean ascOrDes){
		super(ascOrDes);
	}

	@Override
	protected boolean checkDate(Event anEvent, Event secEvent, boolean ascOrDes) {
		return ( ( (anEvent.getStartDate().getTimeInMillis() >= secEvent.getStartDate().getTimeInMillis() ) && 
				(ascOrDes) ) || 
				(anEvent.getStartDate().getTimeInMillis() < secEvent.getStartDate().getTimeInMillis()) && 
				(!ascOrDes));
				
	}

}
