package generator;

import java.util.ArrayList;
import java.util.List;

public class contextClass {
	String classname ="contextClass";
	List<Parameter> params = new ArrayList<Parameter>();
	String name;
	public contextClass(List<Parameter> p, String name){
		params=p;
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}

}
