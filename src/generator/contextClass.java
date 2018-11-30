package generator;

import java.util.ArrayList;
import java.util.List;

import static generator.cbrgenerator.randomWithRange;

public class ContextClass {
	String className ="contextClass";
	List<Parameter> params = new ArrayList<Parameter>();
	String name;
	public ContextClass(List<Parameter> p, String name){
		params=p;
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}

	public String getClassName() {
		return className;
	}

	public List<Context> getContexts (int contexts) {
		List<Context> ctxlist = new ArrayList<>();

		while(ctxlist.size()<contexts){
			boolean similar = false;
			Parameter firstParam = params.get(randomWithRange(0,params.size()-1));
			paramValue firstParamValue = firstParam.getRandomParamValue();
			Parameter secondParam = params.get(randomWithRange(0,params.size()-1));
			paramValue secondParamValue = secondParam.getRandomParamValue();
			Parameter thirdParam = params.get(randomWithRange(0, params.size()-1));
			paramValue thirdParamValue = thirdParam.getRandomParamValue();

			for(Context c:ctxlist){
				if(c.isSimilar(firstParamValue, secondParamValue, thirdParamValue)){
					similar=true;
					break;
				}
			}
			if(!similar){
				Context newContext = new Context(firstParamValue,secondParamValue, thirdParamValue);
				newContext.setName("ctx"+ctxlist.size());
				newContext.setModule("module" + ctxlist.size());
				ctxlist.add(newContext);
			}
		}
		return ctxlist;
	}

	public void printContexts(int numberOfContexts){
		System.out.println("\n% Contexts");
		List<Context> contextList = this.getContexts(numberOfContexts);
		int contextIndex = 0;
		int index = 0;
		for(Context c : contextList){
			System.out.print("context(\""+ c.getName()+"\").");
			System.out.print(" hasName(\""+ c.getName()+"\",\"" + c.getParamValues().get(0).getName() + "_" + c.getParamValues().get(1).getName() + "_"+c.getParamValues().get(2).getName() + "\").");
			System.out.print(" hasModule(\"" + c.getName()+ "\",\"" + c.getModule()+ "\").");
			System.out.print(" hasContextClass(\"" + c.getName() + "\",\"" + this.getName()+"\"). \n");

			for(paramValue p : c.getParamValues()){
				System.out.print("hasParamValue(\"" + c.getName() + "\",\"" + p.getParent().getName() + "\",\"" + p.getName()+ "\"). ");
				index++;
			}
			System.out.println();
			contextIndex++;
		}
	}

	public void printDetParamValues(){
		System.out.println(" % Determine Parameter Values");
		for(Parameter p : params){
			for(String s : p.getDetParamValues()){
				paramValue randomParam = p.getRandomParamValue();
				System.out.print("detParamValue(" + p.getBusinessCase().getClassName() + ",\"" + p.getName() + "\",\"" +  s + "\") :- businessCase("+ p.getBusinessCase().getClassName()+"), " +
						"hasInterestSpec(" + p.getBusinessCase().getClassName() + ", " + p.getBusinessCase().getInterestSpec().getName()+"), " +
						"hasDescProp(" + p.getBusinessCase().getInterestSpec().getName() +",\""+ p.getName() +"\"," + randomParam.getName().substring(0,1) + "), " + randomParam.getName().toLowerCase()+"("
						+ randomParam.getName().substring(0,1)+"), hasDescProp(" + randomParam.getName().substring(0,1)+",\"keineAhnung\"," + s + ").");

				System.out.println();
			}
		}
	}
}
