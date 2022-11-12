// NameComponent.java
import java.util.*;
import java.awt.*;
import javax.swing.*;

/*
 Maintains a collection of NameRecords and graphs their data.
*/
public class NameComponent extends JComponent  {
	
	// first decade
	public static int START = 1900;
	
	//number of decades
	public static int DECADES = 11;
	
	//colors
	public static final Color[] COLORS = new Color[] {Color.BLACK, Color.RED, Color.BLUE, Color.DARK_GRAY};
	
	
	// height and width of component
	public static int height = 600;
	public static int width = 600;
	
	
	//Maintain array list of selected nameRecords
	
	public static ArrayList <NameRecord> ivar = new ArrayList<NameRecord>();
	
	// Space at top and bottom of graph
	public static final int SPACE = 20;
	
	// dry initial size of the component.
	public NameComponent(){
		setPreferredSize(new  Dimension(height,  width));
		
	}
	
	// runs when the component window got resized.
	// updates height , width and gap to match new dimensions of window.
	// repaints after updating values
	public void responseResize(int new_height, int new_width) {
		height = new_height;
		width = new_width;
		
		repaint();
	}
	
	// add a name to ivar array list, then repaint 
	public void addName(NameRecord record) {
		ivar.add(record);
		repaint();
		
	}
	
	// clear the all name records in the ivar array
	public void clearAll() {
		if(ivar.size()>0) {
			ivar.clear();
			repaint();		
		}else {
			Toolkit.getDefaultToolkit().beep();
		}
		
	}
	
	// clear the first name record of the ivar array
	public void clearOne() {
		if(ivar.size()>0) {
			ivar.remove(0);
			repaint();
		}else {
			Toolkit.getDefaultToolkit().beep();
		}
	}
	
	// draw graph lines and labels
	public void paintComponent(Graphics g) {
		
		// horizontal line in the top
		g.drawLine(0 , SPACE , width , SPACE);
		
		//add  vertical line and labels according to the number of decades.
		for (int i = 0;i < DECADES ; i++) {	
			int xPosition =(width * i)/ DECADES;
			Integer decadeInt = START + (i * 10);
			String decade = decadeInt.toString();
			g.drawLine( xPosition , 0 , xPosition , height);
			g.drawString(decade,  xPosition + 5, height - 5);
		}
		
		// horizontal line in the bottom
		g.drawLine(0 , height-SPACE , width , height-SPACE);
				
		
		// this for loop iterates over name components objects in ivar array.
		for (int i = 0; i < ivar.size();i++) {
			
			// holds x and y coordinates in graph line start
			int graphX;
			int graphY;
			
			// holds x and y coordinates of graph line end.
			int graphX1;
			int graphY1;
			
			// holds the popularity rank of each name
			ArrayList <Integer> rank = ivar.get(i).getPopularityRankByDecade();
			
			// selects a color to draw graph line.
			g.setColor(COLORS[i%4]);
			
			// this iterates over the ranks array list
			for(int j = 0; j< DECADES-1 ;j++) {
				
				// set line fragment start x
				graphX = (width * j)/ DECADES;
				
				//  set the line fragment start y
				if(rank.get(j) > 1000 || rank.get(j) ==0 ) {
					graphY =  height-SPACE;	
				}else {
					graphY = ((height- 2 * SPACE) * rank.get(j)) / 1000 + SPACE;
				}
				
				// set the line fragment end x
				graphX1 = (width * (j+1))/ DECADES;
				
				// set the line fragment end y
				if(rank.get(j+1) > 1000 || rank.get(j+1) ==0 ) {
					graphY1 =  height-SPACE;	
				}else {
					graphY1 = ((height -2 * SPACE) * rank.get(j+1)) / 1000+ SPACE;
				}
				
				// draw the line according to given coordinates.
				g.drawLine( graphX , graphY , graphX1 , graphY1);
				
				// draw the name and rank at each and every decades
				g.drawString(ivar.get(i).getName() +" "+ rank.get(j) ,  graphX + 2, graphY);
				
				
				// draw the name and rank for the final decade.
				if(j == DECADES-2) {
					g.drawString(ivar.get(i).getName() +" "+ rank.get(j+1) ,  graphX1 + 2, graphY1);
				}
				
			}
		}
		
		
		
		
	}

}


