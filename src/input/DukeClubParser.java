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

        clubNodeMap.put("allday", "All Day?");
        clubNodeMap.put("Description", "Description");
        clubNodeMap.put("link", "Link");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        System.out.println(nodeName);
        String nodeText = node.getTextContent();

        if (nodeName.equals("summary")) curEvent.mySubject = nodeText;
        else if (nodeName.equals("location"))
        {
            Stack<Node> locStack = new Stack<Node>();
            NodeList locList = node.getChildNodes();
            for (int i = 0; i < locList.getLength(); i++)
            {
                locStack.push(locList.item(i));
            }
            while (!locStack.isEmpty())
            {
                Node current = locStack.pop();
                if (current.getNodeName().equals("address"))
                {
                    curEvent.detailMap.put("Location", current.getTextContent());
                    System.out.println("    "+current.getTextContent());
                }
            }
            System.out.println("     location");
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
        // categories, category, split on "/" and take last value
        else if (nodeName.equals("start"))
        {
            Stack<Node> startStack = new Stack<Node>();
            NodeList startList = node.getChildNodes();
            for (int i = 0; i < startList.getLength(); i++)
            {
                startStack.push(startList.item(i));
            }
            while (!startStack.isEmpty())
            {
                Node current = startStack.pop();
                curEvent = startParsing(current, curEvent, startCal);
            }
            return curEvent;
        }
        else if (nodeName.equals("end"))
        {
            Stack<Node> endStack = new Stack<Node>();
            NodeList startList = node.getChildNodes();
            for (int i = 0; i < startList.getLength(); i++)
            {
                endStack.push(startList.item(i));
            }
            while (!endStack.isEmpty())
            {
                Node current = endStack.pop();
                curEvent = endParsing(current, curEvent, endCal);
            }
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


    private Event startParsing (Node current, Event curEvent, Calendar startCal)
    {
        String nodeName = current.getNodeName();
        String nodeText = current.getTextContent();
        if (nodeName.equals("fourdigityear"))
        {
            startCal.set(Calendar.YEAR, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitmonth"))
        {
            startCal.set(Calendar.MONTH, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitday"))
        {
            startCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigithour24"))
        {
            startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitminute"))
        {
            startCal.set(Calendar.MINUTE, Integer.parseInt(nodeText));
        }
        curEvent.myStart = (GregorianCalendar) startCal;
        return curEvent;
    }


    private Event endParsing (Node current, Event curEvent, Calendar endCal)
    {
        String nodeName = current.getNodeName();
        String nodeText = current.getTextContent();
        if (nodeName.equals("fourdigityear"))
        {
            endCal.set(Calendar.YEAR, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitmonth"))
        {
            endCal.set(Calendar.MONTH, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitday"))
        {
            endCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigithour24"))
        {
            endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(nodeText));
        }
        else if (nodeName.equals("twodigitminute"))
        {
            endCal.set(Calendar.MINUTE, Integer.parseInt(nodeText));
        }
        curEvent.myEnd = (GregorianCalendar) endCal;
        return curEvent;
    }

}
