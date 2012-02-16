import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;


public class DukeBasketballEvent extends Event
{
	private static int fileCount = 0;
	
    public boolean myIsAllDayEvent;
    // deal with all day events
    public String myDescription;
    public String myLocation;
    public String myPriority; // level like low
    public String myPrivacy; // yes or no
    public String myReminderOnOff; // on or off
    public GregorianCalendar myReminder;
    public String mySensitivity; // a level like normal
    public String myShowTimeAs; // no clue, but normally 2

    public DukeBasketballEvent(){
    
    }

    public DukeBasketballEvent (String subject,
                  int startYear,
                  int startMonth,
                  int startDay,
                  int startHour,
                  int startMinute,
                  int endYear,
                  int endMonth,
                  int endDay,
                  int endHour,
                  int endMinute,
                  String description,
                  String location,
                  String priority,
                  String privacy,
                  String reminderOnOff,
                  int reminderYear,
                  int reminderMonth,
                  int reminderDay,
                  int reminderHour,
                  int reminderMinute,
                  String sensitivity,
                  String showTimeAs)
    {
        mySubject = subject;
        myStart.set(startYear, startMonth, startDay, startHour, startMinute);
        myEnd.set(endYear, endMonth, endDay, endHour, endMinute);

        myReminder = null;
        if (reminderOnOff.equals("On")) myReminder.set(reminderYear,
                                                       reminderMonth,
                                                       reminderDay,
                                                       reminderHour,
                                                       reminderMinute);
        myDescription = description;
        myLocation = location;
        myPriority = priority;
        myPrivacy = privacy;
        mySensitivity = sensitivity;
        myShowTimeAs = showTimeAs;
    }



    public boolean isThereAReminder ()
    {
        return myReminderOnOff.equalsIgnoreCase("on");
    }
    public GregorianCalendar getReminderDate ()
    {
        return myReminder;
    }
    public String getLocation(){
        return myLocation;
    }
    public String getPrivacy ()
    {
        return myPrivacy;
    }
    public String getPriority()
    {
        return myPriority;
    }
    public String getSensitivity ()
    {
        return mySensitivity;
    }
    public String getShowTimeAs ()
    {
        return myShowTimeAs;
    }



	public String generateDetailsHTML() {
		// TODO Add code to output element to HTML
		String html =  "<html> \n" +
				"<body> \n" +
				"<h4>"+mySubject+"</h4>" +
						"<p>Description: "+myDescription+"</p>" +
						"<p>Location: "+myLocation+"</p>" +
						"<p>Priority: "+myPriority+"</p>" +
						"<p>Privacy: "+myPrivacy+"</p>" +
						"<p>Sensitivity: "+mySensitivity+"</p>" +
						"<br /><a href=\"Calendar.html\">Back to Calendar</a>" +
				"</body>" +
				"</html>";
		String filename = "DukeDetail"+fileCount+".html";
		FileWriter fstream;
		try {
			fstream = new FileWriter(filename);
			fileCount++;
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(html);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filename;
		}
	}
