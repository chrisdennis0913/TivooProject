import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private List<Event> EventList;

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
    
    public List<Event> getListOfEvents(){
        return EventList;       
    }
    
    public DukeBasketballEvent parseEvent(Node node){
        NamedNodeMap nnm = node.getAttributes();
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        DukeBasketballEvent event = new DukeBasketballEvent();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        Calendar reminderCal = new GregorianCalendar();
        
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
                    // TODO Auto-generated catch block
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
                    // TODO Auto-generated catch block
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
            else if(nodeName.equals("AllDayEvent")){
                event.myIsAllDayEvent = Boolean.parseBoolean(nodeText);
            }
            else if(nodeName.equals("ReminderOnOff"))
                event.myReminderOnOff = nodeText;
            else if(nodeName.equals("ReminderDate")){
                Date date = null;
                try {
                    date = df.parse(nodeText);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }               
                //reminderCal.setTime(date);
                reminderCal.set(Calendar.DAY_OF_MONTH, date.getDate());
                reminderCal.set(Calendar.MONTH, date.getMonth());
                reminderCal.set(Calendar.YEAR, date.getYear());
            }
            else if(nodeName.equals("ReminderTime")){
                String[] hms = nodeText.split(":");
                reminderCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                reminderCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                String[] secAM_PM = hms[2].split(" ");
                reminderCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0])); 
            }
            else if(nodeName.equals("Description"))
                event.myDescription = nodeText;
            else if(nodeName.equals("Location"))
                event.myLocation = nodeText;
            else if(nodeName.equals("Priority"))
                event.myPriority = nodeText;
            else if(nodeName.equals("Private"))
                event.myPrivacy = nodeText;
            else if(nodeName.equals("Sensitivity"))
                event.mySensitivity = nodeText;
            else if(nodeName.equals("Showtimeas"))
                event.myShowTimeAs = nodeText;
            NodeList list = current.getChildNodes();
            for(int i=0;i<list.getLength();i++){
                stack.push(list.item(i));
            }                   
        }   
        event.myReminder = (GregorianCalendar) reminderCal;
        event.myStart = (GregorianCalendar) startCal;
        event.myEnd = (GregorianCalendar) endCal;
        
        return event;
            
        
    }

    public Document parserXML (File file)
        throws SAXException,
            IOException,
            ParserConfigurationException
    {
        return DocumentBuilderFactory.newInstance()
                                     .newDocumentBuilder()
                                     .parse(file);
    }

}
