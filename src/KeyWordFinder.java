import java.util.ArrayList;
import java.util.List;


public class KeyWordFinder {
	public List<Event> keywordFinder (List<Event> myEvents, String keyword){
		ArrayList<Event> keyList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			if (e.getSubject().indexOf(keyword) != -1){
				keyList.add(e);
			}
		}
		return keyList; 
	}

}
