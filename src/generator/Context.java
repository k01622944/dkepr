package generator;

import java.util.ArrayList;
import java.util.List;

public class Context {
    String name ="";
    String module="";
    List<paramValue> paramValues = new ArrayList<>();

    public Context(paramValue first, paramValue second, paramValue third){
        paramValues.add(first);
        paramValues.add(second);
        paramValues.add(third);
    }

    /*Method to check if a Context is similar to another Context*/
    public boolean isSimilar(paramValue one, paramValue two, paramValue three){
        return paramValues.contains(one) && paramValues.contains(two) && paramValues.contains(three);
    }

    public String contextNameToString(){
        return ("context(\""+ this.name +"\").");
    }

    public String hasNameToString(){
        return (" hasName(\""+ this.name +"\",\"" + this.paramValues.get(0).getName() + "_" + this.paramValues.get(1).getName() + "_"+this.paramValues.get(2).getName() + "\").");
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
        for(paramValue p : this.paramValues){
            output+=("hasParamValue(\"" + this.name + "\",\"" + p.getParent().getName() + "\",\"" + p.getName()+ "\"). ");
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
