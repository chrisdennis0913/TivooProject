import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Processor {
    public List<DukeBasketballEvent> timeFrameFinder (List<DukeBasketballEvent> myEvents, GregorianCalendar start, GregorianCalendar end){
        ArrayList<DukeBasketballEvent> timeList = new ArrayList<DukeBasketballEvent> ();
        
        for (DukeBasketballEvent e: myEvents){
            if ( (e.getStartDate().getTimeInMillis() >= start.getTimeInMillis()) && 
                    (e.getStartDate().getTimeInMillis() <= end.getTimeInMillis()) )
                timeList.add(e);
        }
        return timeList;
    }
    
    public List<DukeBasketballEvent> keywordFinder (List<DukeBasketballEvent> myEvents, String keyword){
        ArrayList<DukeBasketballEvent> keyList = new ArrayList<DukeBasketballEvent> ();
        
        for (DukeBasketballEvent e: myEvents){
            if (e.getSubject().startsWith(keyword)){
                keyList.add(e);
            }
        }
        return keyList; 
    }
    
    public static void main (String[] args){
        DukeBasketBallParser input = new DukeBasketBallParser(); 
        
        List<DukeBasketballEvent> listofEvents = input.getListOfEvents();
        Processor process = new Processor ();
        
        List<DukeBasketballEvent> returnList = process.keywordFinder(listofEvents, "Duke");
        for (DukeBasketballEvent e: returnList){
            System.out.println (e.getSubject());
        }
    }
    
}
