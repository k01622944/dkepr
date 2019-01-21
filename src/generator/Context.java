package generator;

import java.util.ArrayList;
import java.util.List;

public class Context {
    String name ="";
    String module="";
    List<Parameter> parameters;
    List<paramValue> paramValues;

    public Context(List<Parameter> parameters){
        this.parameters = parameters;
        this.paramValues = getRandomParamValues();
    }

    private List<paramValue> getRandomParamValues(){
        if(this.parameters==null)return null;
        List<paramValue> paramValues = new ArrayList<>();
        for(Parameter p : this.parameters){
           paramValues.add(p.getRandomParamValue());
        }
        return paramValues;
    }

    public String contextNameToString(){
        return ("context(\""+ this.name +"\").");
    }

    public String hasNameToString(){
        String str = "";
        int i = 0;
        for(paramValue p : this.paramValues) {
            if(i<this.paramValues.size()-1){
                str+=(p.getName() + "_");
            } else {
                str+= p.getName() + "\").";
            }
            i++;
        }
        return (" hasModule(\"" + str);
    }

    public String hasModuleToString(){
        return (" hasModule(\"" + this.name + "\",\"" + this.module + "\").");
    }

    public String hasContextClassToString(){
        return (" hasContextClass(\"" + this.name + "\",\"" + this.getName()+"\"). \n");
    }

    public String hasParameterToString(){
        String output ="";
        int index = 0;
        //sollte sich der Generator die Werte merken??
        for(Parameter p : this.parameters){
            output+=("hasParamValue(\"" + this.name + "\",\"" + p.getName() + "\",\"" + this.paramValues.get(index).getName() + "\"). ");
            index++;
        }
        output+='\n';
        return output;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getModule() {
        return module;
    }

    public void setModule(String module){
        this.module=module;
    }
    public List<paramValue> getParamValues() {
        return paramValues;
    }
}
