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

	public static void main(String[] args) {

		Properties p = new Properties();

		int contexts = p.getamountc();
		int paramCount = p.getamountparam();

		List<Parameter> parameter = new ArrayList<Parameter>();
		for(int i = 0; i<paramCount;i++){
			parameter.add(new Parameter());
		}

		//Kontextklasse
		ContextClass contexClass = new ContextClass(parameter, "aimCtx");

		System.out.println("%----------------------------------------------------------------------");
		System.out.println("% GENERIC COMPONENTS");
		System.out.println("%----------------------------------------------------------------------");

		System.out.println(contexClass.getClassName() + "(\"" + contexClass.getName() + "\").");

		for(int i =0; i<paramCount;i++){
			System.out.print("hasParameter(\"" + contexClass.getName() + "\",\"" + parameter.get(i).getName() + "\"). ");
		}
		System.out.println("\n");
		for(int i =0; i<paramCount; i++){
			System.out.println("parameter(\"" + parameter.get(i).getName() + "\").");
		}

		//Kontexte
		List<Context> contextList = contexClass.getContexts(contexts);

		System.out.println("\n%------------------------------------------------------------------------");
		System.out.println("% DEFINING PARAMETERS, PARAMETER VALUES, CONTEXTS, AND DETpARAMvALUE");
		System.out.println("%------------------------------------------------------------------------");
		//Ausgabe paramValues f�r jeden Parameter
		for(int i = 0; i < parameter.size(); i++){
			for(int j = 0; j< parameter.get(i).paramValues.size();j++){
				System.out.print("paramValue(\"" + parameter.get(i).getName() +  "\",\"" + parameter.get(i).paramValues.get(j).getName() + "\"). ");
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
					System.out.print("covers(\"" + rootValue.getName() + "\",\"" + parameter.get(i).getParamValues().get(z).getName() + "\"). ");
				}
				System.out.println();
			}
		}
		//Ausgabe der Kontexte
		contexClass.printContexts(contexts);

		System.out.println();
		//Ausgabe der Determine Parameter Values
		contexClass.printDetParamValues();

	}
}
