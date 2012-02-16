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

public class XMLtvParser extends InputParser
{
    private Document doc = null;
    private List<Event> EventList;

    public XMLtvParser ()
    {
        try
        {
            doc = parserXML(new File("NFL.xml"));
            List<Event> EventList = new ArrayList<Event>();
            //visit(doc, 0, EventList);
            NodeList nl = doc.getElementsByTagName("row");
            for(int i=0; i<nl.getLength();i++){
                EventList.add(parseEvent(nl.item(i).getFirstChild().getNextSibling()));
            }
            this.EventList = EventList;
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
    }
    
    public List<Event> getListOfEvents(){
        Collections.sort(EventList);
    	return EventList;       
    }
    
    public XMLtvEvent parseEvent(Node node){
        String nnm = node.getNodeValue();
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        XMLtvEvent event = new XMLtvEvent();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Calendar startCal = new GregorianCalendar();
        Calendar endCal = new GregorianCalendar();
        
        while(!stack.isEmpty()){
            Node current = stack.pop();
            //check if it is one of the categories you want, and populate the corresponding field in event
            String nodeName = current.getNodeName();
            String nodeText = current.getTextContent();
            
            if(nodeName.equals("Col1")){ //subject
                event.mySubject = nodeText;
                event.myDescription = nodeText;
            }
            else if (nodeName.equals("Col2")) //description
            	event.mySource = nodeText;
            else if (nodeName.equals("Col3")) //season
            	event.mySeason = nodeText;
            else if(nodeName.equals("Col8")){ //start time and date
                String[] dateTime = nodeText.split("\\s+");
            	Date date = null;
                try {
                    date = df.parse(dateTime[0]);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }         
                
                
                String[] hms = dateTime[1].split(":");
                startCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hms[0]));
                startCal.set(Calendar.MINUTE,Integer.parseInt(hms[1]));
                startCal.set(Calendar.SECOND, Integer.parseInt(hms[2])); 
                
                startCal.set(Calendar.DAY_OF_MONTH, date.getDate());
                startCal.set(Calendar.MONTH, date.getMonth());
                startCal.set(Calendar.YEAR, date.getYear());
            }
            else if(nodeName.equals("Col9")){  //end time and date
                String[] dateTime = nodeText.split("\\s+");
                Date date = null;
                try {
                    date = df.parse(nodeText);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
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
            else if(nodeName.equals("Col15")) //location
                event.myLocation = nodeText;
            if(current.getNextSibling() != null)
            	if(current.getNextSibling().getNextSibling() != null)
            		stack.push(current.getNextSibling().getNextSibling());
              
        }
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
