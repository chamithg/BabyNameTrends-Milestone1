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
class NameFrame extends JFrame implements ActionListener
{
	
	private  NameComponent nameComp;
	// create swing components 
	private JButton cancel;
	private JButton ok;
	private JTextField searchBox; 
	
	
	// Controls if main() does a doSearch()
	public static final boolean SEARCH = false;
	
	// arraylist to hold Name record objects.
	public static ArrayList <NameRecord> ivar = new ArrayList<NameRecord>();
	
	// initiate string variable to hold search inquery
	public static String target ="";
	
	
	
	public NameFrame() {
		setTitle("Graph");
		setSize(600,700);
		Container content = getContentPane( );
		content.setLayout(new BorderLayout( ));
		nameComp = new NameComponent();
		content.add(nameComp, BorderLayout.CENTER);
		nameComp.setFocusable(true);
		nameComp.requestFocusInWindow( );
		setVisible(true);
		
		// add a component listner to catch the changes of the window size.
		nameComp.addComponentListener(new ComponentAdapter() {
		      @Override
		      public void componentResized(ComponentEvent e) {
		    	  
		    	// when resize detected, all the response resize method in name component
		    	// passing new height and width of window.
		        nameComp.responseResize(e.getComponent().getHeight()-100,e.getComponent().getWidth());
		      }
		    });
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		NameFrame frame = new NameFrame();
		frame.read("names-data.txt");
		
		// calls do search method if search is true
		if (SEARCH) {
			frame.doSearch();
		}					
	}
	
	
	//displays search window
	public void showInputDialog() {		
		
		setTitle("Input");
		setSize(300, 150);
		setLayout(null);
		
		// add label  and search field
		JLabel label = new JLabel("Search Names");
		label.setBounds(50,10,100,20);
		searchBox=new JTextField("");
		searchBox.setBounds(50,40,200,20);
		
		// add buttons
		cancel=new JButton("Cancel");
		cancel.setBounds(50,80,80,30);  
		ok=new JButton("Ok");
		ok.setBounds(150,80,80,30);
		
		
		// add action listners
		cancel.addActionListener(this);
		ok.addActionListener(this);
		

		// add elements to searchBox
		add(cancel);
		add(ok);
		add(label);
		add(searchBox);
		setVisible(true);
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
	
	
	// Perform search according for target value
	private void search(String target) {
		
		// Arraylist holds search results.
		ArrayList <String> searchResult = new ArrayList<String>();
		
		// iterates over the arraylist generated from read function.
		for( int i =0; i< ivar.size(); i++) {
			if(ivar.get(i).getName().toLowerCase().contains(target.toLowerCase())) {
				
				// create a string with name and best year, append it to search results.
				String temp =  ivar.get(i).getName() + " " + ivar.get(i).bestYear();
				searchResult.add(temp);
			}
		}
		
		
		// this indicates the start of search results
		System.out.println("\n\n*** Search Results *** \n");
		
		// iterate over search results and print out the results.
		for( int i = 0 ; i< searchResult.size(); i++) {
			System.out.println(searchResult.get(i));
		}
		// this indicates the end of search results
		System.out.println("\n\n*** End of search results *** \n");
	}
	
	// calls display function of search box
	private void doSearch() {
		showInputDialog();
		
	}
	
	
	// handles button events of search box
	// calls search functions after each event to print up to date search results.
	public void actionPerformed( ActionEvent e) {
		if(e.getSource().equals(ok)){
			target = searchBox.getText();
			search(target);
		}else if(e.getSource().equals(cancel)) {
			
			// on click of cancel, returns all available data without search.
			target = "";
			searchBox.setText("");
			search(target);
		}
		
	}
		
	
	
	

	
	
}