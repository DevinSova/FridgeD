package hackers.purdue.firstbusinesscompany.fridged;

/**
 * Created by bscholer on 10/15/16.
 */
public class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
