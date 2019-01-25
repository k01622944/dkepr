package generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static generator.cbrgenerator.randomWithRange;

public class ContextClass {
	String className ="contextClass";
	String businessCaseClass = "semNOTAMCASE";
	List<Parameter> params;
	String name;
	List<Context> ctxlist;


	public ContextClass(String name){
		this.params = new ArrayList<Parameter>();
		this.name = name;
	}

	public String generateCbrData(int parameter, int contexts, int paramValues){
		String output= classNameToString() + "\n" + paramsToString(parameter) + "\n" + contextsToString(contexts) + "\n" + detParamValuesToString() + "\n" + businessCaseToString();
		try{
			PrintWriter outputStream = new PrintWriter("cbr_output.txt");
			outputStream.println(output);
			outputStream.close();
			System.out.println("done");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return output;
	}

	public String getName(){
		return this.name;
	}

	public String getClassName() {
		return className;
	}

	public String classNameToString(){
		return (this.className + "(\"" + this.name + "\").\n" + "businessCaseClass(\"" + this.businessCaseClass + "\").\n");
	}

	public String paramsToString(int paramCount){
		for(int i = 0; i<paramCount;i++) {
			params.add(new Parameter());
		}

		if (this.params==null) return null;
		String hasParameter = "";
		String parameter = "";
		String paramValue = "% -----------------------------------------------------------------------------\n" +
				"% DEFINING PARAMETERS, PARAMETER VALUES, CONTEXTS, AND DETpARAMvALUE\n" +
				"% -----------------------------------------------------------------------------\n" +
				"% Parameter values and their hierarchy\n";
		String covers = "";

		for(int i =0; i<this.params.size(); i++){
			hasParameter+=("hasParameter(\"" + this.className + "\",\"" + this.params.get(i).getName() + "\"). \n");
			parameter+=("parameter(\"" + this.params.get(i).getName() + "\").\n");
			paramValue+=(this.params.get(i).paramValuesToString());
			covers+=(this.params.get(i).coversToString());
		}

		return hasParameter +'\n' + parameter +'\n' + paramValue +'\n' + covers;
	}

	public List<Context> getContexts (int contexts) {
		this.ctxlist = new ArrayList<>();
		List<paramValue> paramValues = new ArrayList<>();

		while(ctxlist.size()<contexts){
			Context newContext = new Context(this.params);
			newContext.setName("ctx"+ctxlist.size());
			newContext.setModule("module" + ctxlist.size());
			ctxlist.add(newContext);
		}
		return this.ctxlist;
	}

	public String contextsToString(int numberOfContexts){
		String output = "%Contexts\n";
		List<Context> contextList = this.getContexts(numberOfContexts);
		int contextIndex = 0;
		int index = 0;
		for(Context c : contextList){
			output+=c.contextNameToString();
			output+=c.hasNameToString();
			output+=c.hasModuleToString();
			output+=(" hasContextClass(\"" + c.getName() + "\",\"" + this.getName()+"\"). \n");
			output+= c.hasParameterToString();
			contextIndex++;

		}
		return output;
	}

	public String detParamValuesToString(){
		String output = (" % Determine Parameter Values\n");
		for(Parameter p : params){
			output += p.detParamValueToString();
		}
		return output;
	}

	public String businessCaseToString(){
		//String output ="hasBusinessCaseClass(BC," + this.businessCaseClass + "\") :- businessCase(BC).\n";
		String output = "";
		int index=0;
		for(int i = 0; i < this.params.size(); i++){
			for(Parameter p : this.params){
				output+=("businessCase(\"bc" + index + "\"). hasDescProp(\"bc" + index + "\", \"" + p.getDescProperty() + "\", \"" + p.getRandomParamValue().getName() + "\").\n");
			}
			index++;
			output+='\n';
		}

		return output;
	}
}
