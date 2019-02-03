package generator_inheritance;

import java.util.ArrayList;

public class Rule {
	private String name;
	private Atom headAtom;
	private ArrayList<Atom> bodyAtoms;
	private int startPos;
	private int rulePos;
	
	public Rule(Generator generator){
		name = generator.generateString(5);
		bodyAtoms = new ArrayList<>();
		int bodys = generator.generateInt(1, 4);
		ArrayList<String> terms = new ArrayList<>();
		for(int i = 0; i < bodys; i++){
			if(generator.generateInt(0, 1) == 0){
				bodyAtoms.add(new RelationalAtom(generator, terms));
			} else {
				bodyAtoms.add(new NonRelationalAtom(generator, terms));
			}
		}
		headAtom = new RelationalAtom(terms, generator);
	}
	
	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getRulePos() {
		return rulePos;
	}

	public void setRulePos(int rulePos) {
		this.rulePos = rulePos;
	}

	public String getName() {
		return name;
	}

	public Atom getHeadAtom() {
		return headAtom;
	}

	public ArrayList<Atom> getBodyAtoms() {
		return bodyAtoms;
	}
	
	public String toString(int position, String name, Generator generator){
		String code = "";
		startPos=position;
		code+="hasAnnotation(\""+name+":"+rulePos+"\",\""+name+":"+position+"\").\n";
		code+="annotation(\""+name+":"+position+"\").\n";
		code+="hasName(\""+name+":"+position+"\",\"label\").\n";
		position++;
		code+="hasArgument(\""+name+":"+startPos+"\",\""+name+":"+position+"\",0).\n";
		code+="term(\""+name+":"+position+"\").";
		code+="hasSerialization(\""+name+":"+position+"\",\"\"\""+this.name+"\"\"\").\n";
		
		generator.updatePosition(position+1);
		return code;
	}
	

}
