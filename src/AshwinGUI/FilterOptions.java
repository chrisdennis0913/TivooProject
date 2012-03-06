package AshwinGUI;

import input.Event;
import input.InputParser;
import input.InputParser.ParserFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import output.GenerateCalendar;

import processor.EndTimeSorter;
import processor.KeyWordFinder;
import processor.NameSorter;
import processor.Processor;
import processor.StartTimeSorter;
import processor.TimeFrameFinder;



public class FilterOptions extends JFrame{
	JPanel pane;
	JTextField keyText, timeText, sortText;
	JCheckBox inc, asc;

	private FilterOptions () {	
		pane = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pane, BorderLayout.CENTER);
		pane.setPreferredSize(new Dimension(390,300));

		pane.add(new JLabel("Keyword ", JLabel.CENTER));
			keyText = new JTextField (15);
		pane.add(keyText);
		pane.add(new JLabel("Time (start & end = y/m/d & y/m/d)", JLabel.LEFT));
			timeText = new JTextField (15);
		pane.add(timeText);
			inc = new JCheckBox("Exclude events");
		pane.add(inc);
		
		pane.add(new JLabel("-----------------------------------------------------------------------------------", JLabel.LEFT));
		
		pane.add(new JLabel("Sort Method ", JLabel.LEFT));
			sortText = new JTextField (6);
		pane.add(sortText);
			asc = new JCheckBox ("Descending events");  
		pane.add(asc); 

		JButton button = new JButton("OK"); 
		pane.add(button);
		button.addActionListener(new ActionListen());
		
		pack ();
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		FilterOptions fil = new FilterOptions();
	}

	
	public class ActionListen implements ActionListener{
		
		public File fileChooser (){
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				return file;
			} 
			else 
				return null;
		}

		public void actionPerformed(ActionEvent evt) {
			String newkey = keyText.getText();
			String newtime = timeText.getText();
			String newsort = sortText.getText();
			boolean excbutton = !inc.isSelected();
			boolean desbutton = !asc.isSelected();
			
			List<Event> eventList = null;
			setVisible(false);
			File file = fileChooser();
			if (file.getName().startsWith("Duke") ){
				InputParser parser = InputParser.ParserFactory.generate("DukeBasketBall.xml");
		        eventList = parser.getListOfEvents();
			}
			else if (file.toString().startsWith("NFL") ){
				InputParser parser = InputParser.ParserFactory.generate("NFL.xml");
		        eventList = parser.getListOfEvents();
			}
			else if (file.toString().startsWith("Google") ){
				InputParser parser = InputParser.ParserFactory.generate("GoogleCalSample.xml");
		        eventList = parser.getListOfEvents();
			}
			else if (file.toString().startsWith("tv")){
				InputParser parser = InputParser.ParserFactory.generate("tv.xml");
		        eventList = parser.getListOfEvents();
			}
			
			Processor process = new Processor(eventList);
			if (newkey!=null) {
				List<String> list = new ArrayList<String>();
				list.add(newkey);
				process.addFilter(new KeyWordFinder(list, excbutton)); 
			}
			if (newtime!=null){
				String[] timesplitters = newtime.split("&");
				GregorianCalendar gStartCal = getGregorianCal (timesplitters[0]);
				GregorianCalendar gEndCal = getGregorianCal (timesplitters[1]);
				process.addFilter(new TimeFrameFinder(gStartCal, gEndCal, excbutton));
			}			
			if (newsort.equalsIgnoreCase("name")){
				process.addFilter(new NameSorter(desbutton)); 
			}
			if (newsort.equalsIgnoreCase("start")){
				process.addFilter(new StartTimeSorter(desbutton)); 

			}
			if (newsort.equalsIgnoreCase("end")){
				process.addFilter(new EndTimeSorter(desbutton)); 

			}			
			
			List<Event> ev = process.process(); 

			GenerateCalendar GC = new GenerateCalendar(ev);
			GC.generate(null, null);
			try{
            HTMLExample foo = new HTMLExample("file:Calendar.html");
			}
			catch (Exception e){
				System.out.println ("Error!");
			}
			
		}
		
		public GregorianCalendar getGregorianCal (String newtime){
			DateFormat dateChange = new SimpleDateFormat("y/M/d");
			Date newdate = null;
			try{
			newdate = dateChange.parse(newtime);
			}
			catch (Exception e){System.out.println ("Error!");}
			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(newdate);
			return gDate;
		}
		
	}
}
