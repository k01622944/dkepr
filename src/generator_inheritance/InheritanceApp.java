package generator_inheritance;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class InheritanceApp {
	
	public String testInheritance(int rules, int facts, int annotations, int tests){
		long start = System.currentTimeMillis();
		String code = "";
		for(int i = 0; i <tests;i++){
			Generator generator = new Generator();
			generator.generateData(rules, facts, annotations);
			code += ""+(i+1)+".er Durchgang:\n\n" + generator.generateCode()+"\n\n";
		}
		try {
			PrintWriter outputStream = new PrintWriter("inheritancePerformance.txt");
			outputStream.println(code);
			outputStream.close();
			System.out.println("done");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long totalTime = end-start;
		long avgTime = totalTime/tests;
		String totalTimeDB = generateTimeString(totalTime);
		String avgTimeDB = generateTimeString(avgTime);
		
		return totalTimeDB + ";" + avgTimeDB;
		
		
//		System.out.println(code);
	}
	
	public String generateTimeString(long time){
		String formated = "";
		long ms = time%1000;
		long seconds = time/1000;
		long minutes = seconds/60;
		seconds = seconds%60;
		
		formated = minutes+"m"+seconds+"s"+ms+"ms";
		return formated;
	}
}
