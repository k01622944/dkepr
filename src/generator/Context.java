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
