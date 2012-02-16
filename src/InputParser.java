
public class InputParser
{
    public static class ParserFactory{
        public static InputParser generate(String filename){
            if(filename.equals("DukeBasketBall.xml")) return new DukeBasketBallParser();
            else if (filename.equals("GoogleCalendar")) return new GoogleCalendarParser();
            else return null;
        }
    }
    

    
}
