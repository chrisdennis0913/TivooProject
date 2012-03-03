package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Stack;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DukeClubParser extends InputParser
{
    public DukeClubParser ()
    {
        super("DukeClubsSample.xml", "event");
    }

    public Event subParsing (Node node,
                             Event curEvent,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> clubNodeMap)
    {
        clubNodeMap.put("description", "Description");
        clubNodeMap.put("link", "Link");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.equals("summary")) curEvent.mySubject = nodeText;
        else if (nodeName.equals("address"))
        {
            curEvent.detailMap.put("Location", nodeText);
            return curEvent;
        }
        else if (nodeName.equals("categories"))
        {
            Stack<Node> catStack = new Stack<Node>();
            NodeList catList = node.getChildNodes();
            Node firstCategory = catList.item(0);
            NodeList catList2 = firstCategory.getChildNodes();
            
            curEvent.detailMap.put("Category", firstCategory.getTextContent());
            for (int i = 0; i < catList2.getLength(); i++)
            {
                catStack.push(catList2.item(i));
            }
            while (!catStack.isEmpty())
            {
                Node current = catStack.pop();
                if (current.getNodeName().equals("value"))
                {
                    String[] wordArray= current.getTextContent().split("/");
                    curEvent.detailMap.put("Category", wordArray[wordArray.length-1]);
                }
            }
            return curEvent;
        }
        else if (nodeName.equals("start") || nodeName.equals("end"))
        {
            NodeList startList = node.getChildNodes();
            GregorianCalendar tempCal = new GregorianCalendar();
            for (int i = 0; i < startList.getLength(); i++)
            {
               tempCal =(GregorianCalendar) parseMyDate(startList.item(i),startCal);
            }
            if(nodeName.equals("start")) curEvent.myStart = tempCal;
            else if (nodeName.equals("end")) curEvent.myEnd = tempCal;
            return curEvent;
        }
        else if (clubNodeMap.containsKey(nodeName)) curEvent.detailMap.put(clubNodeMap.get(nodeName),
                                                                           nodeText);

        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            stack.push(list.item(i));
        }
        curEvent.myStart = (GregorianCalendar) startCal;
        curEvent.myEnd = (GregorianCalendar) endCal;

        return curEvent;
    }


    private Calendar parseMyDate (Node current, Calendar myCal)
    {
        String nodeName = current.getNodeName();
        String nodeText = current.getTextContent();
        if (nodeName.equals("fourdigityear"))
        {
            myCal.set(Calendar.YEAR, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitmonth"))
        {
            myCal.set(Calendar.MONTH, Integer.parseInt(nodeText)-1);
        }
        else if (nodeName.equals("twodigitday"))
        {
            myCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigithour24"))
        {
            myCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitminute"))
        {
            myCal.set(Calendar.MINUTE, Integer.parseInt(nodeText));
        }
        return myCal;
    }

}
