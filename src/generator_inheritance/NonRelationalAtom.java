package generator_inheritance;

import java.util.ArrayList;

public class NonRelationalAtom extends Atom{
	private String term;
	private int number;
	private int startPos;

	public NonRelationalAtom(Generator generator, ArrayList<String> bodyTerms){
		super.setRelAtom(false);
		term = generator.generateString(1);
		bodyTerms.add(term);
		number = generator.generateInt(0, 100000);
	}
	
	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public String getTerm() {
		return term;
	}

	public int getNumber() {
		return number;
	}

//	public Atom generateNonRelAtom(Generator generator){
//		term = generator.generateString(1);
//		number = generator.generateInt(0, 100000);
//		return this;
//	}
	
	public String toString(int position, String name, Generator generator){
		String code = "";
		startPos=position;
		code+="hasPositiveBodyAtom(\""+name+":0\",\""+name+":"+position+"\").\n";
		code+="nonRelationalAtom(\""+name+":"+position+"\").\n";
		code+="hasSerialization(\""+name+":"+position+"\",\""+term+"<"+number+"\").\n";
		generator.updatePosition(position+1);
		return code;
	}
}
