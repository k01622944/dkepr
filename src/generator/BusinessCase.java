package generator;

public class BusinessCase {
    String className = "BC";
    String name;
    InterestSpec interestSpec;

    public BusinessCase(){
        this.interestSpec=new InterestSpec();
    }
    public BusinessCase(String name){
        this.name=name;
        this.interestSpec=new InterestSpec();
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public InterestSpec getInterestSpec() {
        return interestSpec;
    }

    public void setName(String name){
        this.name=name;

    }
}
