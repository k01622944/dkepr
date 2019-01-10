package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static generator.cbrgenerator.generateString;

public class paramValue {
	Parameter parent = null;
	String name;
	List<paramValue> covers = new ArrayList<paramValue>();
	private static Random random = new Random();
	
	public paramValue(Parameter parent) {
		this.parent=parent;
		name = generateString();
	}

	public paramValue(String n){this.name=n;}

	public String getName() {
		return name;
	}

	public Parameter getParent(){
		return parent;
	}

	public List<paramValue> getCovers() {
		return covers;
	}
}
