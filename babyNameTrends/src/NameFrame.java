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
	
	
	
	public static void main(String[] args) throws IOException {
		NameFrame frame = new NameFrame();
		frame.read("names-data.txt");

		//if (SEARCH) {
		//	frame.doSearch();
		//}
					
	}
	// read method- iterate through given file/ return exceptions.
	private void read(String fileName) throws IOException {
		
		// arraylist to hold Name record objects.
		ArrayList<NameRecord> ivar = new ArrayList<NameRecord>();
		
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

	
	
}