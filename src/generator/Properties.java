package generator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Properties {

    int amountc;
    int amountparam;
    int amountparamvalues;

    public Properties(){
        String text = readString("config.txt");

        String[] words = saveTxtArray("config.txt");
        for(int i = 0; i < words.length; i++){
            if (words[i].matches("Contexts")){
                this.amountc = Integer.parseInt(words[i+2]);
            }
            if(words[i].matches("Parameter")){
                this.amountparam = Integer.parseInt(words[i+2]);
            }
            if(words[i].matches("ParameterValues")){
                this.amountparamvalues = Integer.parseInt(words[i+2]);
            }
        }

    }

    public  String readString(String file){
        String text = "";
        try{
            Scanner s = new Scanner(new File(file));
            while (s.hasNextLine()){
                text = text + s.next() + " ";
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return text;
    }


    public  String[] saveTxtArray(String file) {
        try {
            int ctr = 0;
            Scanner s1 = new Scanner(new File(file));
            while (s1.hasNextLine()) {
                ctr = ctr + 1;
                s1.next();
            }
            String[] words = new String[ctr];

            Scanner s2 = new Scanner(new File(file));
            for (int i = 0; i < ctr; i++) {
                words[i] = s2.next();
            }
            return words;
        } catch (Exception ex) {
            System.out.println("could not find File");
        }

        return null;
    }



    public int getamountc() {
        return this.amountc;
    }
    public int getamountparam(){
        return this.amountparam;
    }
    public int getamountparamvalues(){
        return this.amountparamvalues;
    }
}
