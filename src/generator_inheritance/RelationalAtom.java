package generator_inheritance;

import java.util.ArrayList;

public class RelationalAtom extends Atom{
	private String name;
	private ArrayList<String> terms;
	private int startPos;
	private boolean fact = false;
	
	/**
	 * Generates a BodyAtom
	 * @param generator
	 * @param bodyTerms
	 */
	public RelationalAtom(Generator generator, ArrayList<String> bodyTerms){
		super.setRelAtom(true);
		terms = new ArrayList<>();
		name = generator.generateString(5);
		generator.addPossibleFactName(name);
		int length = generator.generateInt(1, 4);
		for (int i = 0; i < length; i++){
			String term = generator.generateString(1);
			terms.add(term);
			bodyTerms.add(term);
			
		}
	}
	
	/**
	 * generates a HeadAtom
	 * @param bodyTerms
	 * @param generator
	 */
	public RelationalAtom(ArrayList<String> bodyTerms, Generator generator){
		super.setRelAtom(true);
		terms = new ArrayList<>();
		name = generator.generateString(5);
		for (String s : bodyTerms){
			if(generator.generateInt(0, 1)==0){
				terms.add(s);
			}
		}
	}
	
	/**
	 * generates a fact
	 * @param generator
	 */
	public RelationalAtom(Generator generator, String atomName){
		super.setRelAtom(true);
		terms = new ArrayList<>();
		name = atomName;
		terms.add("\"\""+generator.generateString(2)+"\"\"");
	}
	
	public void setFact(boolean fact){
		this.fact = fact;
	}
	
	public boolean getFact(){
		return fact;
	}
	
	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getTerms() {
		return terms;
	}
	
	public String toString(int position, String name, Generator generator, String bodyHead){
		String code = "";
		startPos=position;
		code+=bodyHead+"(\""+name+":0\", \""+name+":"+startPos+"\").\n";
		code+="relationalAtom(\""+name+":"+startPos+"\").\n";
		code+="hasName(\""+name+":"+startPos+"\", \""+this.name+"\").\n";
		int termPos = 0;
		for(String s : terms){
			position++;
			code+="hasArgument(\""+name+":"+startPos+"\",\""+name+":"+position+"\","+termPos+").\n";
			code+="term(\""+name+":"+position+"\").\n";
			code+="hasSerialization(\""+name+":"+position+"\",\""+s+"\").\n";
			termPos++;
		}
		generator.updatePosition(position+1);
		return code;
	}
}
