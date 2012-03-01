package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TVEventFinder implements SearchInterface{

	private ArrayList<String> key = new ArrayList<String>();
	private boolean incOrExc; 

	public TVEventFinder (List<String> listofTVactors, boolean incOrExc) {
		key.addAll(listofTVactors);
		this.incOrExc = incOrExc;
	}

	public List<Event> search (List<Event> myEvents){
		ArrayList<Event> keyList = new ArrayList<Event> ();

		for (Event e: myEvents){
			if ((e.getSubject().indexOf("TV")) == -1){
				keyList.add(e);
				break;
			}
			for (String k: key){	
				if ((e.getSubject().indexOf(k)) != -1){
					keyList.add(e);
					break;
				}
				else{
					Set<String> detailkey = e.detailMap.keySet();
					for (String let: detailkey){
						if ((e.detailMap.get(let).indexOf(k)) == -1){
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

