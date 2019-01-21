package generator;

import java.util.*;

import static generator.cbrgenerator.generateString;

public class Parameter {
	private static Random random = new Random();
	String name = "Parameter_" + generateString();
	List<paramValue> paramValues = new ArrayList<>();
	BusinessCase businessCase = new BusinessCase();
	String descProperty ="";

	
	public Parameter(){
		for(int i = 0; i<randomWithRange (5,8); i++){
			paramValues.add(new paramValue(this));
		}
		this.descProperty=generateString();
	}

	public String paramValuesToString(){
		if(paramValues==null) return "";
		paramValue rootValue = paramValues.get(0);
		String output="";

			for(int j = 0; j< paramValues.size();j++){
				output+=("paramValue(\"" + this.name +  "\",\"" + this.paramValues.get(j).getName() + "\").");
			}
			output+='\n';

		return output;
	}

	public String coversToString(){
		if(this.paramValues==null) return "";
		String output = "";
		paramValue rootValue = paramValues.get(0);
		//erstes Element von paramValues immer in Hierarchie ganz oben verkn�pft mit beliebigen anderen paramValues durch covers Beziehung
		for (int z = 1; z < paramValues.size(); z++) {
			output+=("covers(\"" + rootValue.getName() + "\",\"" + paramValues.get(z).getName() + "\"). ");
		}
		output+='\n';

		return output;
	}

	public String detParamValueToString(){
		String output ="";
		paramValue randomParam = this.getRandomParamValue();
		output+=("detParamValue(" + this.businessCase.getClassName() + ",\"" + this.name + "\",\"" + "Val" + "\") :- businessCase("+ this.businessCase.getClassName()+"), " +
				"hasDescProp(" + this.businessCase.getClassName() +",\"DescProp_"+ this.descProperty +"\"," + "Val" + ").\n");
		return output;
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

	public BusinessCase getBusinessCase() {
		return businessCase;
	}

}
