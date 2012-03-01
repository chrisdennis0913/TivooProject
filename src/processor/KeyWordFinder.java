package processor;
import input.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class KeyWordFinder implements SearchInterface{
	
	private ArrayList<String> key = new ArrayList<String>();
	private boolean incOrExc; 
	
	public KeyWordFinder (List<String> listofKeywords, boolean incOrExc){
		key.addAll(listofKeywords);
		this.incOrExc = incOrExc;
	}
	
	public List<Event> search (List<Event> myEvents){
		ArrayList<Event> keyList = new ArrayList<Event> ();
		
		for (Event e: myEvents){
			for (String k: key){
				if ((e.getSubject().indexOf(k)) != -1){
					keyList.add(e);
					break;
				}
				else{
					Set<String> detailkey = e.detailMap.keySet();
					for (String let: detailkey){
						if ((e.detailMap.get(let).indexOf(k)) != -1){
							keyList.add(e);
							break;
						}
					}
				}
			}
		}
		
		if (incOrExc)
			return keyList;
		else{
			List<Event> remList = new ArrayList<Event>();
			remList.addAll(myEvents);
			remList.removeAll(keyList);
			return remList;
		}
			
	}
}
