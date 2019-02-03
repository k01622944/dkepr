package generator_inheritance;

import java.util.ArrayList;

public class Generator {
	private String name = "mixed";
	private ArrayList<Rule> rules;
	private ArrayList<RelationalAtom> facts;
	private ArrayList<Annotation> annotations;
	private ArrayList<String> factNames;
	private int position = 0;
	
	public Generator(){
		rules = new ArrayList<>();
		facts = new ArrayList<>();
		annotations = new ArrayList<>();
		factNames = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public ArrayList<RelationalAtom> getFacts() {
		return facts;
	}

	public String generateString(int length){
		String text = "";
		for(int i = 0; i < length; i++){
			char help = (char) ((char)generateInt(0, 25) + 'A');
			text += help;
		}
		return text;
	}
	
	public int generateInt(int minVal, int maxVal){
		int random = 0;
		random = minVal + (int)(Math.random() * ((maxVal-minVal)+1));
		return random;
	}
	
	public String generateAnnotation(){
		switch (generateInt(0,4)){
			case 0: return "inherits";
			case 1: return "module";
			case 2: return "redefines";
			case 3: return "bind";
			default: return "implement";
		}
	}
	
	public void updatePosition(int pos){
		position = pos;
	}
	
	public void addPossibleFactName(String factName){
		factNames.add(factName);
	}
	
	public void generateData(int countRules, int countFacts, int countAnnotations){
		//Generate Rules
		for(int i = 0; i < countRules; i++){
			rules.add(new Rule(this));
		}
		//Generate Facts
		for(int i = 0; i < countFacts; i++){
			facts.add(new RelationalAtom(this, factNames.get(generateInt(0, factNames.size()-1))));
		}
		//Generate Annotations
		for(int i = 0; i < countAnnotations; i++){
			annotations.add(new Annotation(this));
		}
	}
	
	public String generateCode (){
		String code = "program(\""+name+"\").\n";
		//rule code
		for(Rule rule : rules){
			code += "hasRule(\""+name+"\", \""+name+":"+position+"\").\n";
			rule.setRulePos(position);
			code += "rule(\""+name+":"+position+"\").\n";
			position++;
			code += ((RelationalAtom)(rule.getHeadAtom())).toString(position, name, this, "hasPositiveHeadAtom");
			ArrayList<Atom> bodys = rule.getBodyAtoms();
			RelationalAtom relAtom;
			NonRelationalAtom nonRelAtom;
			for(Atom body : bodys){
				if(body.isRelAtom()){
					relAtom = (RelationalAtom)body;
					code += relAtom.toString(position, name, this, "hasPositiveBodyAtom");
				} else {
					nonRelAtom = (NonRelationalAtom)body;
					code += nonRelAtom.toString(position, name, this);
				}
			}
			code+=rule.toString(position, name, this);
		}
		
		//facts code
		for(RelationalAtom fact : facts){
			code += fact.toString(position, name, this, "hasFact");
		}
		
		//annotation code
		for(Annotation a : annotations){
			code += a.toString(position, name, this);
		}
		
		
		
		return code;
	}
}
