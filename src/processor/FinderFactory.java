package processor;


public class FinderFactory {
	public KeyWordFinder CreateKeyWordFinder (){
		return new KeyWordFinder(); 
	}
	
	public TimeFrameFinder CreateTimeFrameFinder (){
		return new TimeFrameFinder(); 
	}

}
