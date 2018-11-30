package generator;

import java.util.*;

import static generator.cbrgenerator.generateString;

public class Parameter {
	private static Random random = new Random();
	String name;
	List<paramValue> paramValues = new ArrayList<>();
	List<String> detParamValues = new ArrayList<>();
	BusinessCase businessCase = new BusinessCase();
	
	public Parameter(){
		this.name =generateString();
		for(int i = 0; i<randomWithRange (5,8); i++){
			paramValues.add(new paramValue(this));
		}
		for(int i = 0; i<randomWithRange(1, 4);i++){
			detParamValues.add(generateString().toUpperCase());
		}
	}
	

	
	int randomWithRange(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;     
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}

	public List<paramValue> getParamValues() {
		return paramValues;
	}
	public String getName() {
		return name;
	}
	public paramValue getRandomParamValue(){
		return paramValues.get(randomWithRange(0,paramValues.size()-1));
	}
	public List<String> getDetParamValues() {
		return detParamValues;
	}

	public BusinessCase getBusinessCase() {
		return businessCase;
	}
}
