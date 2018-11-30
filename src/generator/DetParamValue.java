package generator;


import java.util.List;

import static generator.cbrgenerator.generateString;
import static generator.cbrgenerator.randomWithRange;

public class DetParamValue {
    String name = "";
    InterestSpec interestSpec;
    char descProp;

    //65 und 90

    /*Verdacht auf Bullshit
    public DetParamValue(List<Parameter> parameter){
        name = generateString();
        interestSpec = new InterestSpec();
        descProp = (char) randomWithRange(65,90);
        //interestSpec und descProp sollen verschiedene Namen haben
        while(descProp==interestSpec.getName()){
            descProp=(char) randomWithRange(65,90);
        }
    } */

}
