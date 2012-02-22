import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DukeBasketBallParser extends InputParser
{
    private Document doc = null;

    public DukeBasketBallParser ()
    {
        try
        {
            doc = parserXML(new File("DukeBasketBall.xml"));
            List<Event> EventList = new ArrayList<Event>();
            //visit(doc, 0, EventList);
            NodeList nl = doc.getElementsByTagName("Calendar");
            for(int i=0; i<nl.getLength();i++){
                EventList.add(parseEvent(nl.item(i)));
            }
            this.EventList = EventList;
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
    }
    
    public Event parseEvent(Node node){
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        Event event = new Event();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        
        while(!stack.isEmpty()){
            Node current = stack.pop();
            //check if it is one of the categories you want, and populate the corresponding field in event
            String nodeName = current.getNodeName();
            String nodeText = current.getTextContent();
            
            if(nodeName.equals("Subject"))
                event.mySubject = nodeText;
            else if(nodeName.equals("StartDate")){
                Date date = null;
                try {
                    date = df.parse(nodeText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }               
                //startCal.setTime(date);
                startCal.set(Calendar.DAY_OF_MONTH, date.getDate());
                startCal.set(Calendar.MONTH, date.getMonth());
                startCal.set(Calendar.YEAR, date.getYear());
            }
            else if(nodeName.equals("StartTime")){
                String[] hms = nodeText.split(":");
                startCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                startCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                String[] secAM_PM = hms[2].split(" ");
                startCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0]));               
            }
            else if(nodeName.equals("EndDate")){
                Date date = null;
                try {
                    date = df.parse(nodeText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }               
                //endCal.setTime(date);
                endCal.set(Calendar.DAY_OF_MONTH, date.getDate());
                endCal.set(Calendar.MONTH, date.getMonth());
                endCal.set(Calendar.YEAR, date.getYear());
            }
            else if(nodeName.equals("EndTime")){
                String[] hms = nodeText.split(":");
                endCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                endCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                String[] secAM_PM = hms[2].split(" ");
                endCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0])); 
            }
            else if(nodeName.equals("Description"))
                event.detailMap.put("Description", nodeText);
            else if(nodeName.equals("Location"))
            	event.detailMap.put("Location", nodeText);
            else if(nodeName.equals("Priority"))
            	event.detailMap.put("Priority", nodeText);
            else if(nodeName.equals("Private"))
            	event.detailMap.put("Private", nodeText);
            else if(nodeName.equals("Sensitivity"))
            	event.detailMap.put("Sensitivity", nodeText);
            else if(nodeName.equals("Showtimeas"))
            	event.detailMap.put("Showtimeas", nodeText);
            NodeList list = current.getChildNodes();
            for(int i=0;i<list.getLength();i++){
                stack.push(list.item(i));
            }                   
        }   

        event.myStart = (GregorianCalendar) startCal;
        event.myEnd = (GregorianCalendar) endCal;
        
        return event;
            
        
    }

}
