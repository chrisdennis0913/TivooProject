package input;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GoogleCalParser extends InputParser
{
    public GoogleCalParser ()
    {
        super("GoogleCalSample.xml", "entry");
    }


    public Event subParsing (Node node,
                             Event curEvent,
                             Calendar startCal,
                             Calendar endCal,
                             Map<String, String> googleCalNodeMap)
    {
        googleCalNodeMap.put("description", "Description");
        googleCalNodeMap.put("id", "Link");
        googleCalNodeMap.put("name", "Author");
        googleCalNodeMap.put("email", "Author's Email");

        //check if it is one of the categories you want, and populate the corresponding field in event
        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();

        if (nodeName.startsWith("title")) curEvent.mySubject = nodeText;
        else if (nodeName.startsWith("content"))
        {
            try
            {
                String[] summaryArray = nodeText.split(":");
                if (summaryArray[0].equals("Whin"))
                {
                    String[] singleEventArray = summaryArray[1].split(" ");
                    //parse and put in the dates and times
                    int myMonth = parseMonth(singleEventArray[1]);
                    startCal.set(Calendar.MONTH, myMonth);
                    endCal.set(Calendar.MONTH, myMonth);
                    String dayString = singleEventArray[2];
                    int myDay =
                        Integer.parseInt(dayString.substring(0,
                                                             dayString.length() - 1));
                    startCal.set(Calendar.DAY_OF_MONTH, myDay);
                    endCal.set(Calendar.DAY_OF_MONTH, myDay);
                    String yearString = singleEventArray[3];
                    int myYear = Integer.parseInt(yearString.substring(0, 4));
                    startCal.set(Calendar.YEAR, myYear);
                    endCal.set(Calendar.YEAR, myYear);
                    Character ch = yearString.charAt(0);
                    Character.isDigit(ch);

                    // take care of regular, then do all day events
                    if (Character.isDigit(singleEventArray[4].charAt(0)))
                    {
                        int myHour =
                            Integer.parseInt(singleEventArray[4].substring(0,
                                                                           singleEventArray[4].length() - 2));
                        //check if pm
                        if (singleEventArray[4].substring(singleEventArray[4].length() - 2)
                                               .startsWith("p"))
                        {
                            myHour += 12;
                        }
                        startCal.set(Calendar.HOUR_OF_DAY, myHour);
                        startCal.set(Calendar.MINUTE, 0);

                        int myEndHour =
                            Integer.parseInt(singleEventArray[6].substring(0,
                                                                           singleEventArray[6].length() - 2));
                        //check if pm
                        if (singleEventArray[6].substring(singleEventArray[6].length() - 2)
                                               .startsWith("p"))
                        {
                            myEndHour += 12;
                        }
                        endCal.set(Calendar.HOUR_OF_DAY, myEndHour);
                        endCal.set(Calendar.MINUTE, 0);
                    }
                    else
                    {//all day event
                        startCal.set(Calendar.HOUR_OF_DAY, 0);
                        endCal.set(Calendar.HOUR_OF_DAY, 23);
                        startCal.set(Calendar.MINUTE, 0);
                        endCal.set(Calendar.MINUTE, 59);
                    }

                }
                else if (summaryArray[0].startsWith("Recurring Event"))
                {// treat as all day event
                    // figure out what a duration is
                    startCal.set(Calendar.HOUR_OF_DAY, 0);
                    endCal.set(Calendar.HOUR_OF_DAY, 23);
                    startCal.set(Calendar.MINUTE, 0);
                    endCal.set(Calendar.MINUTE, 59);
                }
            }
            catch (Exception error)
            {
                error.printStackTrace();
            }
            if (nodeText.contains("Event Description")){
                int startIndex=nodeText.indexOf("Event Description");
                String[] endOfNodeText=nodeText.substring(startIndex).split(":");
                curEvent.detailMap.put("Event Description", endOfNodeText[1]);
            }
        }
        else if (googleCalNodeMap.containsKey(nodeName)) curEvent.detailMap.put(googleCalNodeMap.get(nodeName),
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


    private int parseMonth (String month)
    {
        if (month.equals("Jan")) return 1;
        else if (month.equals("Feb")) return 2;
        else if (month.equals("Mar")) return 3;
        else if (month.equals("Apr")) return 4;
        else if (month.equals("May")) return 5;
        else if (month.startsWith("Jun")) return 6;
        else if (month.startsWith("Jul")) return 7;
        else if (month.equals("Aug")) return 8;
        else if (month.equals("Sep")) return 9;
        else if (month.equals("Oct")) return 10;
        else if (month.equals("Nov")) return 11;
        return 12;
    }

}
