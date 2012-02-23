package input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.w3c.dom.Node;

public class XMLtvParser extends InputParser
{
    public XMLtvParser ()
    {
        super("NFL.xml", "row");
    }
    
    
    public Event parseEvent(Node node){
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        Event event = new Event();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        Map<String, String> xmlTVNodeMap= new HashMap<String, String>();
        xmlTVNodeMap.put("Col2", "Source");
        xmlTVNodeMap.put("Col3", "Season");
        xmlTVNodeMap.put("Col15", "Location");
        
        while(!stack.isEmpty()){
            Node current = stack.pop();
            //check if it is one of the categories you want, and populate the corresponding field in event
            String nodeName = current.getNodeName();
            String nodeText = current.getTextContent();
            
            if(nodeName.equals("Col1")){ //subject
                event.mySubject = nodeText;
                event.detailMap.put("Description", nodeText);
            }
            
            else if(nodeName.equals("Col8")){ //start time and date
                
            	String[] dateTime = nodeText.split("\\s+");
            	String[] date = dateTime[0].split("-");                
                
                String[] hms = dateTime[1].split(":");
                startCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                startCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                startCal.set(Calendar.SECOND, Integer.parseInt(hms[2])); 
                
                startCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
                startCal.set(Calendar.MONTH, Integer.parseInt(date[1]));
                startCal.set(Calendar.YEAR, Integer.parseInt(date[0]));
            }
            else if(nodeName.equals("Col9")){  //end time and date
                String[] dateTime = nodeText.split("\\s+");
                Date date = null;
                try {
                    date = df.parse(nodeText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }        
                
                String[] hms = dateTime[1].split(":");
                endCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                endCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                endCal.set(Calendar.SECOND, Integer.parseInt(hms[2])); 
                
                endCal.set(Calendar.DAY_OF_MONTH, date.getDate());
                endCal.set(Calendar.MONTH, date.getMonth());
                endCal.set(Calendar.YEAR, date.getYear());
            }
            else if (xmlTVNodeMap.containsKey(nodeName)) event.detailMap.put(xmlTVNodeMap.get(nodeName), nodeText);
            
            if(current.getNextSibling() != null)
            	if(current.getNextSibling().getNextSibling() != null)
            		stack.push(current.getNextSibling().getNextSibling());
              
        }
        event.myStart = (GregorianCalendar) startCal;
        event.myEnd = (GregorianCalendar) endCal;
        
        return event;           
        
    }



}
