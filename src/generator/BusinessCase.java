package generator;

public class BusinessCase {
    String className = "BC";
    String name;
    InterestSpec interestSpec;

    public BusinessCase(){
        this.name=cbrgenerator.generateString().toLowerCase();
    }

    public String getClassName() {
        return this.className;
    }

    public String getName() {
        return this.name;
    }

    public InterestSpec getInterestSpec() {
        return interestSpec;
    }

    public void setName(String name){
        this.name=name;
    }
}
