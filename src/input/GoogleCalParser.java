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
                if (summaryArray[0].equals("When"))
                {
                    String[] singleEventArray = nodeText.split(" ");
                    //parse and put in the dates and times
                    int myMonth = parseMonth(singleEventArray[2]);
                    startCal.set(Calendar.MONTH, myMonth);
                    endCal.set(Calendar.MONTH, myMonth);

                    String dayString = singleEventArray[3];
                    int myDay =
                        Integer.parseInt(dayString.substring(0,
                                                             dayString.length() - 1));
                    startCal.set(Calendar.DAY_OF_MONTH, myDay);
                    endCal.set(Calendar.DAY_OF_MONTH, myDay);

                    String yearString = singleEventArray[4];
                    int myYear = Integer.parseInt(yearString.substring(0, 4));
                    startCal.set(Calendar.YEAR, myYear);
                    endCal.set(Calendar.YEAR, myYear);
                    Character ch = yearString.charAt(0);
                    Character.isDigit(ch);

                    // take care of regular, then do all day events
                    if (Character.isDigit(singleEventArray[5].charAt(0)))
                    {
                        startCal =
                            parseMyTime(startCal, singleEventArray[5], 2);
                        endCal = parseMyTime(endCal, singleEventArray[7], 10);
                    }
                    else
                    {//all day event
                        startCal = allDayStart();
                        endCal = allDayEnd();
                    }
                }
                else if (summaryArray[0].startsWith("Recurring Event"))
                {
                    //Recurring Event<br> First start: 2011-08-29 14:50:00 EDT <br> Duration: 4500 <br>Where: LSRC 106 <br>Event Status: confirmed
                    String[] recurSplit = nodeText.split(" ");
                    String[] recurDateArray = recurSplit[4].split("-");
                    int recurYear = Integer.parseInt(recurDateArray[0]);
                    int recurMonth = Integer.parseInt(recurDateArray[1]);
                    int recurDay = Integer.parseInt(recurDateArray[2]);

                    startCal.set(Calendar.YEAR, recurYear);
                    endCal.set(Calendar.YEAR, recurYear);
                    startCal.set(Calendar.MONTH, recurMonth);
                    endCal.set(Calendar.MONTH, recurMonth);
                    startCal.set(Calendar.DAY_OF_MONTH, recurDay);
                    endCal.set(Calendar.DAY_OF_MONTH, recurDay);

                    startCal = allDayStart();
                    endCal = allDayEnd();
                }
            }
            catch (Exception error)
            {
                error.printStackTrace();
            }
            if (nodeText.contains("Event Description"))
            {
                int startIndex = nodeText.indexOf("Event Description");
                String[] endOfNodeText =
                    nodeText.substring(startIndex).split(":");
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


    private GregorianCalendar allDayStart ()
    {
        GregorianCalendar myCal = new GregorianCalendar();
        myCal.set(Calendar.HOUR_OF_DAY, 0);
        myCal.set(Calendar.MINUTE, 0);
        return myCal;
    }


    private GregorianCalendar allDayEnd ()
    {
        GregorianCalendar myCal = new GregorianCalendar();
        myCal.set(Calendar.HOUR_OF_DAY, 23);
        myCal.set(Calendar.MINUTE, 59);
        return myCal;
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


    private Calendar parseMyTime (Calendar myCal, String current, int base)
    {
        int myHour;
        //if no colon
        if (!current.contains(":"))
        {

            myHour =
                Integer.parseInt(current.substring(0, current.length() - base));
            myCal.set(Calendar.MINUTE, 0);
        }
        else
        // if colon
        {
            myHour =
                Integer.parseInt(current.substring(0, current.length() - base -
                                                      3));
            int myMin;
            myMin =
                Integer.parseInt(current.substring(current.length() - base - 2,
                                                   current.length() - base));
            myCal.set(Calendar.MINUTE, myMin);
        }
        //check if pm
        if (current.substring(current.length() - base).startsWith("p"))
        {
            myHour += 12;
        }
        myCal.set(Calendar.HOUR_OF_DAY, myHour);
        return myCal;
    }

}
