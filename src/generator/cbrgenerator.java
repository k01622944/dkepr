package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cbrgenerator {
	private static Random random = new Random();
	
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

	public static <T> T getRandomItem(List<T> list) {
	    if(list.isEmpty()){
	        throw new IllegalArgumentException("Liste darf nicht leer sein!");
	    }
	    T item = list.get(random.nextInt(list.size()));
	    return item;
	}
	
	static int randomWithRange(int min, int max)
	{
	   int range = Math.abs(max - min) + 1;     
	   return (int)(Math.random() * range) + (min <= max ? min : max);
	}

	public static void main(String[] args) {
		List<Parameter> parameter = new ArrayList<Parameter>();
		int paramCount = 5;
		
		for(int i = 0; i<paramCount;i++){
			parameter.add(new Parameter());
		}
		//Kontextklasse
		contextClass aimCtx = new contextClass(parameter, "aimCtx");
		System.out.println("%----------------------------------------------------------------------");
		System.out.println("% GENERIC COMPONENTS");
		System.out.println("%----------------------------------------------------------------------");
		
		System.out.println(aimCtx.classname + "(\"" + aimCtx.name + "\").");
		
		for(int i =0; i<paramCount;i++){
			System.out.print("hasParameter(\"" + aimCtx.getName() + "\",\"" + parameter.get(i).name + "\"). ");
		}
		System.out.println("\n");
		for(int i =0; i<paramCount; i++){
			System.out.println("parameter(\"" + parameter.get(i).name + "\").");
		}
		System.out.println("\n%------------------------------------------------------------------------");
		System.out.println("% DEFINING PARAMETERS, PARAMETER VALUES, CONTEXTS, AND DETpARAMvALUE");
		System.out.println("%------------------------------------------------------------------------");
		//Ausgabe paramValues f�r jeden Parameter
		for(int i = 0; i < parameter.size(); i++){
			for(int j = 0; j< parameter.get(i).paramValues.size();j++){
				System.out.print("paramValue(\"" + parameter.get(i).name +  "\",\"" + parameter.get(i).paramValues.get(j).name + "\"). ");
			}
			System.out.println();
		}
		System.out.println();


		//Ausgabe der Covers Beziehungen
		for(int i = 0; i<parameter.size(); i++){
			if(parameter.get(i).getParamValues()!=null) {
				paramValue rootValue = parameter.get(i).getParamValues().get(0);
				//erstes Element von paramValues immer in Hierarchie ganz oben verkn�pft mit beliebigen anderen paramValues durch covers Beziehung
				for (int z = 1; z < parameter.get(i).getParamValues().size(); z++) {
					System.out.print("covers(\"" + rootValue.getName() + "\"," + parameter.get(i).getParamValues().get(z).getName() + "\"). ");
				}
				System.out.println();
			}
		}

		

	}

}
