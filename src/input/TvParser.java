package input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TvParser extends InputParser{

	    public TvParser ()
	    {
	        super("tv.xml", "programme");
	    }
	    private Map<String, String> multiEntryMap;

	    @SuppressWarnings("deprecation")
	    public Event subParsing (Node node,
	                             Event event,
	                             Calendar startCal,
	                             Calendar endCal,
	                             Map<String, String> xmlTVNodeMap)
	    {
	    	multiEntryMap = new HashMap<String, String>();

	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        xmlTVNodeMap.put("title", "Title");
	        xmlTVNodeMap.put("sub-title", "Sub-Title");
	        xmlTVNodeMap.put("desc", "Description");
	        xmlTVNodeMap.put("date", "Date");
	        xmlTVNodeMap.put("category", "Category");
	        xmlTVNodeMap.put("episode-num", "Episode Number");
	        xmlTVNodeMap.put("previously-shown", "Previously Shown");
	        xmlTVNodeMap.put("subtitles", "Subtitles");
	        xmlTVNodeMap.put("value", "Rating");
	        xmlTVNodeMap.put("stereo", "Audio");
	        xmlTVNodeMap.put("director", "Director");
	        xmlTVNodeMap.put("producer", "Producer");
	        xmlTVNodeMap.put("writer", "Writer");
	        
	        multiEntryMap.put("actor", "Actor(s)");
	        multiEntryMap.put("category", "Category");
	        multiEntryMap.put("producer", "Producer(s)");
	        multiEntryMap.put("writer", "Writer(s)");        
	        

	        //check if it is one of the categories you want, and populate the corresponding field in event
	        String nodeName = node.getNodeName();
	        String nodeText = node.getTextContent();

	        if(nodeName.equals("title")){
	        	event.mySubject = nodeText;
	        }
	        if (nodeName.equals("desc"))
	        { //subject
	            event.detailMap.put("Description", nodeText);
	        }
	        else if (nodeName.equals("programme")){
	        	NamedNodeMap n = node.getAttributes();
	        	Node start = n.getNamedItem("start");
	        	Node end = n.getNamedItem("stop");
	        	Node channel = n.getNamedItem("channel");
	        	
	        	startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(start.getNodeValue().substring(8, 10)));
	            startCal.set(Calendar.MINUTE, Integer.parseInt(start.getNodeValue().substring(10, 12)));

	            startCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(start.getNodeValue().substring(6, 8)));
	            startCal.set(Calendar.MONTH, Integer.parseInt(start.getNodeValue().substring(4, 6))-1);
	            startCal.set(Calendar.YEAR, Integer.parseInt(start.getNodeValue().substring(0, 4)));
	            
	            endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(end.getNodeValue().substring(8, 10)));
	            endCal.set(Calendar.MINUTE, Integer.parseInt(end.getNodeValue().substring(10, 12)));

	            endCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(end.getNodeValue().substring(6, 8)));
	            endCal.set(Calendar.MONTH, Integer.parseInt(end.getNodeValue().substring(4, 6))-1);
	            endCal.set(Calendar.YEAR, Integer.parseInt(end.getNodeValue().substring(0, 4)));
	        	
	        }
	        else if(multiEntryMap.containsKey(nodeName)){
	        	String key = multiEntryMap.get(nodeName);
	        	String fieldInfo = event.detailMap.get(key);
	        	if(fieldInfo != null){
	        		fieldInfo += ", " + nodeText;
	        		event.detailMap.put(key, fieldInfo);
	        	}
	        	else{
	        		event.detailMap.put(key, nodeText);
	        	}
	        }
	        else if (xmlTVNodeMap.containsKey(nodeName)){
	        	event.detailMap.put(xmlTVNodeMap.get(nodeName), nodeText);
	        }

	        //if (node.getNextSibling() != null) if (node.getNextSibling().getNextSibling() != null) stack.push(node.getNextSibling().getNextSibling());

	        NodeList list = node.getChildNodes();
	        for (int i = 0; i < list.getLength(); i++)
	        {
	            stack.push(list.item(i));
	        }
	        
	        event.myStart = (GregorianCalendar) startCal;
	        event.myEnd = (GregorianCalendar) endCal;

	        return event;

	    }

	}

