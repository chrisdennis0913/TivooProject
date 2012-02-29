package processor;


public class SorterFactory {
	
	public TimeSorter CreateTimeSorter (ProcessParameters param){
		return new TimeSorter(param); 
	}
	
	public NameSorter CreateNameSorter (ProcessParameters param){
		return new NameSorter(param); 
	}
}
