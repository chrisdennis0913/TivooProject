import java.util.List;


public abstract class InputParser
{
    public abstract List<Event> getListOfEvents();
	
	public static class ParserFactory{
        public static InputParser generate(String filename){
            if(filename.equals("DukeBasketBall.xml")) return new DukeBasketBallParser();

            else if (filename.equals("NFL.xml")) return new XMLtvParser();
            else return null;
        }
    }
    


}
