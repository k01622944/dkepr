package generator;

import static generator.cbrgenerator.randomWithRange;

public class InterestSpec {
    char name;
    public InterestSpec(){
        this.name= (char) randomWithRange(65,90);
    }

    public char getName() {
        return name;
    }
}
