package generator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file = "cbr_output.txt";

        Properties p = new Properties();
        int contexts = p.getamountc();
        int paramCount = p.getamountparam();

        ContextClass contexClass = new ContextClass(paramCount, "aimCtx");

        try{
            PrintWriter outputStream = new PrintWriter(file);
            outputStream.println(contexClass.classNameToString());
            outputStream.println(contexClass.paramsToString());
            outputStream.println(contexClass.contextsToString(contexts));
            outputStream.println(contexClass.detParamValuesToString());
            outputStream.close();
            System.out.println("done");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
