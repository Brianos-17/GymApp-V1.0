package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 * Created by Brian on 03/05/2017.
 */
@Entity
public class Member extends Person {
    private double height;
    private double startingWeight;

    public Member(String email, String firstName, String lastName, String password, String gender, double height, double startingWeight){
        super(email, firstName, lastName, password, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
    }

    //Accessor Methods
    public double getHeight() {
        return height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    //Mutator Methods
    public void setHeight(double height) {
        if ((height >= 1) && (height <= 3)) {
            this.height = height;
        }
    }

    public void setStartingWeight(double startingWeight) {
        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.startingWeight = startingWeight;
        }
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
