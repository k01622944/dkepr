package generator;

import java.util.*;

public class Parameter {
	String name;
	List<paramValue> paramValues = new ArrayList<>();
	private static Random random = new Random();
	
	public Parameter(){
		this.name =generateString();
		for(int i = 0; i<randomWithRange (5,8); i++){
			paramValues.add(new paramValue());
		}
	}
	
	public static String generateString() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
	
	int randomWithRange(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;     
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}
	
	
	
	
}
