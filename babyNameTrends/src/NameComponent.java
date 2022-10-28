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
	
	// space between two lines
	public static int gap = 55;
	
	// height and width of component
	public static int height = 600;
	public static int width = 600;
	
	
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
		gap= (int) Math.floor(new_width / DECADES);
		
		repaint();
	}
	
	// draw graph lines and labels
	public void paintComponent(Graphics g) {
		
		// horizontal line in the top
		g.drawLine(0 , SPACE , width , SPACE);
		
		//add  vertical line and labels according to the number of decades.
		for (int i = 0;i < DECADES ; i++) {	
			int xPosition =gap * i;
			Integer decadeInt = START + (i * 10);
			String decade = decadeInt.toString();
			g.drawLine( xPosition , 0 , xPosition , height);
			g.drawString(decade,  xPosition + 5, height - 5);
		}
		
		// horizontal line in the bottom
		g.drawLine(0 , height-SPACE , width , height-SPACE);
		
		
		
	}

}


