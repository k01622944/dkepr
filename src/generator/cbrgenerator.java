package generator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cbrgenerator {

	private static Random random = new Random();
	
	public static String generateString() {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 5;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    char firstLetter = (char) randomWithRange(65,90);
	    String generatedString = buffer.toString();
	    return firstLetter+generatedString;
	}

	static int randomWithRange(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;     
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}


}
