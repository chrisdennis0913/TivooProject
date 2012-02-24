package input;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Event implements Comparable<Event>
{
	private static int fileCount = 0;
    public String mySubject;
    public GregorianCalendar myStart;
    public GregorianCalendar myEnd;
    public Map<String, String> detailMap = new HashMap<String, String>();
    
    public Event(){
    	
    }
    
	public int compareTo(Event other) {
		
		if(myStart.get(Calendar.YEAR) < other.myStart.get(Calendar.YEAR)) return -1;
		else if(myStart.get(Calendar.YEAR) > other.myStart.get(Calendar.YEAR)) return 1;
			
		if(myStart.get(Calendar.MONTH) < other.myStart.get(Calendar.MONTH)) return -1;
		else if(myStart.get(Calendar.MONTH) > other.myStart.get(Calendar.MONTH)) return 1;
		
		if(myStart.get(Calendar.DAY_OF_MONTH) < other.myStart.get(Calendar.DAY_OF_MONTH)) return -1;
		else if(myStart.get(Calendar.DAY_OF_MONTH) > other.myStart.get(Calendar.DAY_OF_MONTH)) return 1;

		return 0;
	}
	public String toString(){
	    StringBuilder result = new StringBuilder();
	    String newLine = System.getProperty("line.separator");
	    result.append( this.getClass().getName() );
	    result.append( " Object {" );
	    result.append(newLine);

	    //determine fields declared in this class only (no fields of superclass)
	    Field[] fields = this.getClass().getDeclaredFields();

	    //print field names paired with their values
	    for ( Field field : fields  ) {
	      result.append("  ");
	      try {
	        result.append( field.getName() );
	        result.append(": ");
	        //requires access to private field:
	        result.append( field.get(this) );
	      }
	      catch ( IllegalAccessException ex ) {
	        System.out.println(ex);
	      }
	      result.append(newLine);
	    }
	    result.append("}");

	    return result.toString();
	}
    
    public String getSubject ()
    {
        return mySubject;
    }
    public GregorianCalendar getStartDate ()
    {
        return myStart;
    }
    public GregorianCalendar getEndDate ()
    {
        return myEnd;
    }
    
    public String generateDetailsHTML() {
		// TODO Add code to output element to HTML
    	StringBuffer html = new StringBuffer();
		html.append( "<html> \n" +
				"<body> \n" + 
				"<h4>"+mySubject+"</h4>");
				
		Set<String> detailNames = detailMap.keySet();
		for(String detailName : detailNames){
			html.append("<p>"+detailName+": " + detailMap.get(detailName) + "</p>");			
		}
					
		html.append("<br /><a href=\"Calendar.html\">Back to Calendar</a>" +
				"</body>" +
				"</html>");
		String filename = "DukeDetail"+fileCount+".html";
		FileWriter fstream;
		try {
			fstream = new FileWriter(filename);
			fileCount++;
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(html.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filename;
		}

    class MalformedDateException extends Exception {
		  public MalformedDateException() {
		  }
		 
		  public MalformedDateException(String msg) {
		    super(msg);
		  }
	}
    
}
