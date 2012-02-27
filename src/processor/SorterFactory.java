package processor;


public class SorterFactory {
	public static final int SORT_BY_STARTTIME = 0;
	public static final int SORT_BY_ENDTIME = 1;
	public static final int SORT_BY_NAME = 2;

	
	public TimeSorter CreateTimeSorter (boolean start, boolean ascend){
		return new TimeSorter(start, ascend); 
	}
	
	public NameSorter CreateNameSorter (boolean ascend){
		return new NameSorter(ascend); 
	}
}
