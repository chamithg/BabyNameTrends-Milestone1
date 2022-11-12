import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

// NameRecord.java
/*
 Encapsulates the data for a name:
 the name string and its rank numbers over the years
*/
public class NameRecord {
	public static final int START = 1900;
	public static final int DECADES = 11;
	
	// define variables to hold name and array of ranks in each decade.
	public String name;
	public ArrayList <Integer> popularityRankByDecade = new ArrayList <Integer>();
	
	
	// constructor- finds and assign data to name and popularityRankByDecade variables.	
	public NameRecord(String singleLine) {
		
		// splits the input string (line of the input data) and store in a array.
		String[] splited = singleLine.split("\\s+");
		
		
		// iterates over spliced array
		for (int i = 0; i < splited.length;i++) {
			
			// Assign index 0 to name.
			// Assign indexes beyond 0 to popularityRankByDecade arraylist.
			if(i == 0) {
				name = splited[0];
			}else {
				popularityRankByDecade.add(Integer.parseInt(splited[i]));
			}
		}
	}
	
	// getter method to return name
	String getName() {		
		return name;
	}
	
	
	// getter method to return popularityRankByDecade
	ArrayList<Integer> getPopularityRankByDecade() {
		return popularityRankByDecade;
		
	}
	
	// return the value or a given decade
	int getRank(int decade) {
		
		// find the index represent the given decade.
		int decadeIndex = (decade- START)/10;
		
		// returns value of that index
		return popularityRankByDecade.get(decadeIndex);
	}
	
	// Returns the best ranked year.
	int bestYear() {
		// the least expected rank will be 1500.
		int bestRank = 1500;
		int bestYear = START;
		
		// iterates over the arraylist of ranks.
		for(int i = 0; i < DECADES; i++) {
			// find better ranks than current bestrank value.
			// replace the bestYear and Best rank accordingly.
			if (popularityRankByDecade.get(i)< bestRank  && popularityRankByDecade.get(i)!= 0) {
				bestRank = popularityRankByDecade.get(i);
				bestYear =START + (10 * i);
			}
		}
		
		
		
		return bestYear;
				
				
	}
}

