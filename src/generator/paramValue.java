package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class paramValue {
	String name;
	List<paramValue> covers = new ArrayList<paramValue>();
	private static Random random = new Random();
	
	public paramValue() {
		name = generateString();
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
	
	public String getRandomParamValue() {
		String p ="";
	    if(covers.isEmpty()){
	        throw new IllegalArgumentException("Liste darf nicht leer sein!");
	    }
	    
		p = covers.get(random.nextInt(covers.size())).name;
	    while(p.equals(covers.get(0))){
	    	p = covers.get(random.nextInt(covers.size())).name;
	    }
	    return p;
	}

}
