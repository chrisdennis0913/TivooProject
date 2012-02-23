import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DukeBasketBallParser extends InputParser
{
    public DukeBasketBallParser ()
    {
        super("DukeBasketball.xml", "Calendar");
    }

    @SuppressWarnings("deprecation")
    public Event subParsing (Node node,
                             Event curEvent,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> bBallNodeMap)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        bBallNodeMap.put("Description", "Description");
        bBallNodeMap.put("Location", "Location");
        bBallNodeMap.put("Priority", "Priority");
        bBallNodeMap.put("Private", "Private");
        bBallNodeMap.put("Sensitivity", "Sensitivity");
        bBallNodeMap.put("Showtimeas", "Showtimeas");
        bBallNodeMap.put("Description", "Description");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.equals("Subject")) curEvent.mySubject = nodeText;
        else if (nodeName.equals("StartDate"))
        {
            Date date = null;
            try
            {
                date = df.parse(nodeText);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            //startCal.setTime(date);
            startCal.set(Calendar.DAY_OF_MONTH, date.getDate());
            startCal.set(Calendar.MONTH, date.getMonth());
            startCal.set(Calendar.YEAR, date.getYear());
        }
        else if (nodeName.equals("StartTime"))
        {
            String[] hms = nodeText.split(":");
            startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
            startCal.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
            String[] secAM_PM = hms[2].split(" ");
            startCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0]));
        }
        else if (nodeName.equals("EndDate"))
        {
            Date date = null;
            try
            {
                date = df.parse(nodeText);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            //endCal.setTime(date);
            endCal.set(Calendar.DAY_OF_MONTH, date.getDate());
            endCal.set(Calendar.MONTH, date.getMonth());
            endCal.set(Calendar.YEAR, date.getYear());
        }
        else if (nodeName.equals("EndTime"))
        {
            String[] hms = nodeText.split(":");
            endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
            endCal.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
            String[] secAM_PM = hms[2].split(" ");
            endCal.set(Calendar.SECOND, Integer.parseInt(secAM_PM[0]));
        }
        else if (bBallNodeMap.containsKey(nodeName)) curEvent.detailMap.put(bBallNodeMap.get(nodeName),
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
}
