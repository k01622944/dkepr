package generator_inheritance;

import java.util.ArrayList;

public class Annotation {
	private int startPos;
	private String name;
	private ArrayList<String> terms;
	
	public Annotation(Generator generator){
		name = generator.generateAnnotation();
		terms = new ArrayList<>();
		if (name.equals("redefines")){
			generateRedefines(generator);
		} else if(name.equals("bind")){
			generateBind(generator);
		} else if(name.equals("implement")){
			generateImplement(generator);
		} else {
			generateAnnotation(generator);
		}
	}

	private void generateRedefines(Generator generator){
		ArrayList<Rule> rules = generator.getRules();
		Rule r = rules.get(generator.generateInt(0, rules.size()-1));
		terms.add("\"\"\""+r.getName()+"\"\"\"");
		terms.add(generateTerm(generator));
	}
	
	public void generateBind(Generator generator){
		ArrayList<RelationalAtom> facts = generator.getFacts();
		RelationalAtom f = facts.get(generator.generateInt(0, facts.size()-1));
		terms.add("\"\"\""+f.getName()+"\"\"\"");
		terms.add(generateTerm(generator));
	}
	
	public void generateImplement(Generator generator){
		int countTerms = generator.generateInt(1, 4);
		for (int i = 0; i < countTerms; i++){
			terms.add(generateTerm(generator));
		}
	}
	
	private void generateAnnotation(Generator generator){
		terms.add(generateTerm(generator));
	}
	
	private String generateTerm (Generator generator){
		return "\"\"\""+generator.generateString(4)+"\"\"\"";
	}
	
	public String toString(int position, String name, Generator generator){
		String code = "";
		startPos=position;
		code+="hasAnnotation(\""+name+"\",\""+name+":"+position+"\").\n";
		code+="annotation(\""+name+":"+position+"\").\n";
		code+="hasName(\""+name+":"+position+"\",\""+this.name+"\").\n";
		position++;
		
		int termPos = 0;
		for(String s : terms){
			code+="hasArgument(\""+name+":"+startPos+"\",\""+name+":"+position+"\","+termPos+").\n";
			code+="term(\""+name+":"+position+"\").";
			code+="hasSerialization(\""+name+":"+position+"\","+s+").\n";
			termPos++;
		}
		generator.updatePosition(position+1);
		return code;
	}
}
