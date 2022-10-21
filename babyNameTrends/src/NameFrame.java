// NameFrame.java
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/*
 Frame that contains the NameComponent and manages the overall application.
 Stores the overall collection of NameRecords, installs
 the NameComponent, deals with the controls and messaging the
 NameComponent.
*/
class NameFrame extends JFrame 
{
	// Controls if main() does a doSearch()
	public static final boolean SEARCH = true;
	
	// arraylist to hold Name record objects.
	public static ArrayList <NameRecord> ivar = new ArrayList<NameRecord>();
	
	// initiate string variable to hold search inquery
	public static String target ="sam";
	
	public static void main(String[] args) throws IOException {
		NameFrame frame = new NameFrame();
		
		
		// creating temporary search window
		
		frame.setTitle("Input");
		frame.setSize(300, 150);
		frame.setLayout(null);
		
		// add label  and search field
		JLabel label = new JLabel("Search Names");
		label.setBounds(50,10,100,20);
		JTextField textField=new JTextField("");
		textField.setBounds(50,40,200,20);
		
		// add buttons
		JButton cancel=new JButton("Cancel");
		cancel.setBounds(50,80,80,30);  
		JButton ok=new JButton("Ok");
		ok.setBounds(150,80,80,30);
		
		
		// set button actions
		//TODO
		
		
		frame.add(cancel);
		frame.add(ok);
		frame.add(label);
		frame.add(textField);
		frame.setVisible(true);
		
		

		frame.read("names-data.txt");
		
		
		if (SEARCH) {
			frame.doSearch();
		}					
	}
	// read method- iterate through given file/ return exceptions.
	private void read(String fileName) throws IOException {	
		
		// read file line by line , store each line's string values in singleLine.
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String singleLine;
			while((singleLine = reader.readLine())!= null) {
				
				// creates nameRecord object passing singleLine string value.
				NameRecord line = new NameRecord(singleLine);
				
				// store the object in ivar Arraylist. 
				ivar.add(line);
			}
			reader.close();
			
		// returns exceptions when file is not present.	
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
	}
	
	
	// 
	private ArrayList<String> search(String target) {
		ArrayList <String> searchResult = new ArrayList<String>();
		for( int i =0; i< ivar.size(); i++) {
			if(ivar.get(i).getName().toLowerCase().contains(target.toLowerCase())) {
				String temp =  ivar.get(i).getName() + " " + ivar.get(i).bestYear();
				searchResult.add(temp);
			}
		}
		
		
		return searchResult;
		
		
	}
	private void doSearch() {
		
		ArrayList <String> results =search(target);
		
		for( int i = 0 ; i< results.size(); i++) {
			System.out.println(results.get(i));
		}
	}
	
	
	

	
	
}