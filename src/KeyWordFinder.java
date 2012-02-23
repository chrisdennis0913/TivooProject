import java.util.ArrayList;
import java.util.List;


public class KeyWordFinder implements FinderInterface{
	
	private String key;
	public KeyWordFinder (String keyword){
		key = keyword;
	}
	
	public List<Event> finder (List<Event> myEvents){
		ArrayList<Event> keyList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			if (e.getSubject().indexOf(key) != -1){
				keyList.add(e);
			}
		}
		return keyList; 
	}

}
