package generator_inheritance;

public class Atom {
	private boolean relAtom;
	
	public boolean isRelAtom() {
		return relAtom;
	}

	public void setRelAtom(boolean relAtom) {
		this.relAtom = relAtom;
	}

//	public Atom generateBodyAtom(Generator generator){
//		Atom body = new Atom();
//		relAtom = generator.generateInt(0, 1) == 0 ? true : false;
//		if(relAtom){
//			RelationalAtom rel = new RelationalAtom();
//			body = rel.generateRelAtom(generator);
//		} else{
//			NonRelationalAtom nonRel = new NonRelationalAtom();
//			body = nonRel.generateNonRelAtom(generator);
//		}
//		
//		return body;
//	}
}
