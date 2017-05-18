package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 03/05/2017.
 *
 * Trainer class, creates an instance of a trainer who can review members assessments and add comments
 */
@Entity
public class Trainer extends Person {
    private String speciality;

    /**
     * Each trainer stores an arraylist of all members
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<Member>();

    /**
     * Constructor for class Trainer, one of the concrete implementations of class Person
     *
     * @param email String inherited from super class
     * @param firstName String inherited from super class
     * @param lastName String inherited from super class
     * @param password String inherited from super class
     * @param gender String inherited from super class
     * @param speciality String representing trainers speciality
     */
    public Trainer (String email, String firstName, String lastName, String password, String gender, String speciality) {
        super(email, firstName, lastName, password, gender);
        setSpeciality(speciality);
    }

    //Mutator Methods
    /**
     * Method to return trainer speciality
     * @return String of this trainers speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    //Accessor Methods
    /**
     * Method which sets trainers speciality
     * @param speciality String representing this trainers speciality
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * Method which will allow objects of class trainer to be found via their email
     * @param email String email to be searched
     * @return The first trainer found the passed paramater
     */
    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Method to check password for authentication purposes
     * @param password String password to be entered during log in process
     * @return true if the passed in password matches this trainers password
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
