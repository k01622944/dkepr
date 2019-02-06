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

	public String generateCbrData(int parameter, int contexts, int paramValues, int runs) {
			String output = classNameToString() + "\n" + paramsToString(parameter,paramValues) + "\n" + contextsToString(contexts) + "\n" +
							detParamValuesToString() + "\n" + businessCaseToString() + "\n" + staticCode();
			try {
				PrintWriter outputStream = new PrintWriter("cbr_output.txt");
				outputStream.println(output);
				outputStream.close();
				System.out.println("done");
			} catch (FileNotFoundException e) {
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

	public String paramsToString(int paramCount, int paramV){
		for(int i = 0; i<paramCount;i++) {
			params.add(new Parameter(paramV));
		}

		if (this.params==null) return null;
		String hasParameter = "";
		String parameter = "";
		String paramValue = "% -----------------------------------------------------------------------------\n" +
				"% DEFINING PARAMETERS, PARAMETER VALUES, CONTEXTS, AND DETpARAMvALUE\n" +
				"% -----------------------------------------------------------------------------\n" +
				"% Parameter values and their hierarchy\n";
		String covers = "";
		int help = 0;

			for (int i = 0; i < this.params.size(); i++) {
				hasParameter += ("hasParameter(\"" + this.className + "\",\"" + this.params.get(i).getName() + "\"). \n");
				parameter += ("parameter(\"" + this.params.get(i).getName() + "\").\n");

			}
		for(int j = 0; j < paramV;j++) {
			if(help == this.params.size()){
				help = 0;
			} else {
				paramValue += (this.params.get(help).paramValuesToString());
				covers += (this.params.get(help).coversToString());
				help++;
			}
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
	public String staticCode(){
		String output = "% -----------------------------------------------------------------------------\n" +
				"% DERIVING RELATIONSHIPS OF PARAM VALUES AND CONTEXTS\n" +
				"% -----------------------------------------------------------------------------\n" +
				"% transitive and transitive-reflexive covers\n" +
				"tCovers(Pval,Cval) :- tCovers(Pval,X), covers(X,Cval).\n" +
				"tCovers(Pval,Cval) :- covers(Pval,Cval).\n" +
				"trCovers(Pval,Cval) :- tCovers(Pval,Cval). trCovers(Pval,Pval):- paramValue(_,Pval).\n" +
				"\n" +
				"% Context Hierarchy\n" +
				"paramCover(P,C,Param):- hasParamValue(C,Param,Pval), hasParamValue(P,Param,Pval2), trCovers(Pval2,Pval).\n" +
				"notParamCover(C,P,Param):- context(C), hasContextClass(C,CtxCl), hasParameter(CtxCl,Param), context(P), not paramCover(C,P,Param).\n" +
				"ctxInherits(C,P) :- paramCover(P,C,_), not notParamCover(P,C,_).\n";
		output += '\n';
		output += '\n';
		output += "% -----------------------------------------------------------------------------\n" +
				"% DETERMINE RELEVANT CONTEXTS AND THE MOST SPECIFIC RELEVANT CONTEXT\n" +
				"% -----------------------------------------------------------------------------\n" +
				"bcParamCover(BC,Ctx,Param) :- hasParamValue(Ctx,Param,PVal), detParamValue(BC,Param,PVal2), trCovers(PVal,PVal2).\n" +
				"notBcParamCover(BC,Ctx,Param) :- businessCase(BC), context(Ctx), hasContextClass(Ctx,CtxCl), hasParameter(CtxCl,Param), not bcParamCover(BC,Ctx,Param).\n" +
				"detRelevantCtxs(BC,Ctx) :- bcParamCover(BC,Ctx,X), not notBcParamCover(BC,Ctx,Y).\n" +
				"\n" +
				"hasRelevantDescendant(BC,Ctx) :- detRelevantCtxs(BC,Ctx), detRelevantCtxs(BC,Ctx2), ctxInherits(Ctx2,Ctx), not w_ctxIdent(Ctx,Ctx2), Ctx!=Ctx2.\n" +
				"detMostSpecificCtx(BC,Ctx) :- detRelevantCtxs(BC,Ctx), not hasRelevantDescendant(BC,Ctx).\n" +
				"\n" +
				"@output(\"detMostSpecificCtx\"). @post(\"detMostSpecificCtx\",\"orderby(1,2)\").\n" +
				"@output(\"detRelevantCtxs\"). @post(\"detRelevantCtxs\",\"orderby(1,2)\").\n" +
				"\n" +
				"% -----------------------------------------------------------------------------\n" +
				"% WARNINGS\n" +
				"% -----------------------------------------------------------------------------\n" +
				"w_incompleteCtxSpec(C) :- parameter(P), context(C), not hasParamValue(C,P,_).\n" +
				"@output(\"w_incompleteCtxSpec\").\n" +
				"\n" +
				"ctxDiffParamValue(Ctx1,Ctx2) :- context(Ctx1), context(Ctx2), hasParamValue(Ctx1,P,PVal1), hasParamValue(Ctx2,P,PVal2), PVal1!=PVal2.\n" +
				"w_ctxIdent(Ctx1,Ctx2) :- context(Ctx1), context(Ctx2), not ctxDiffParamValue(Ctx1,Ctx2),Ctx1!=Ctx2.\n" +
				"@output(\"w_ctxIdent\").\n";
		return output;
	}

}
